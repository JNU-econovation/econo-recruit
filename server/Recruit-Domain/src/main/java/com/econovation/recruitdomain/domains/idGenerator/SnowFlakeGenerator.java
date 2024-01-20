package com.econovation.recruitdomain.domains.idGenerator;

import java.io.Serializable;
import java.time.Instant;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SnowFlakeGenerator implements IdentifierGenerator {

    private static final int CASE_ONE_BITS = 10;
    private static final int CASE_TWO_BITS = 9;
    private static final int SEQUENCE_BITS = 4;

    private static final int maxSequence = (int) (Math.pow(2, CASE_ONE_BITS) - 1); // 2^5-1

    private static final long CUSTOM_EPOCH = 1420070400000L; // 41bit
    private volatile long sequence = 0L;
    private int case_one = 10;
    private int case_two = 0;
    private volatile long lastTimestamp = -1L;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        return nextId();
    }

    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;
        return makeId(currentTimestamp);
    }

    private Long makeId(long currentTimestamp) {
        long id = 0;

        id |= (currentTimestamp << CASE_ONE_BITS + CASE_TWO_BITS + SEQUENCE_BITS);
        id |= (case_one << CASE_TWO_BITS + SEQUENCE_BITS);
        id |= (case_two << SEQUENCE_BITS);
        id |= sequence;

        return id;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
