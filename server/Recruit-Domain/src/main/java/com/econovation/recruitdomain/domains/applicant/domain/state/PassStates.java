package com.econovation.recruitdomain.domains.applicant.domain.state;

import com.econovation.recruitdomain.domains.applicant.exception.ApplicantWrongStateException;
import com.econovation.recruitdomain.domains.applicant.exception.NotOperatedException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum PassStates {
    NON_PROCESSED("non-processed") {
        @Override
        public PassStates pass(PeriodStates period) {
            if (isPassable(period)) return PassStates.FIRST_PASSED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public PassStates nonPass(PeriodStates period) {
            if (isNonPassable(period)) return PassStates.FIRST_FAILED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public boolean isPassable(PeriodStates period) {
            return period.equals(PeriodStates.FIRST_DISCUSSION);
        }

        @Override
        public boolean isNonPassable(PeriodStates period) {
            return period.equals(PeriodStates.FIRST_DISCUSSION);
        }
    },
    FIRST_PASSED("first-passed") {
        @Override
        public PassStates pass(PeriodStates period) {
            if (isPassable(period)) return PassStates.FINAL_PASSED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public PassStates nonPass(PeriodStates period) {
            if (isNonPassable(period)) {
                if (period.equals(PeriodStates.FIRST_DISCUSSION)) return PassStates.FIRST_FAILED;
                else return PassStates.FINAL_FAILED;
            } else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public boolean isPassable(PeriodStates period) {
            return period.equals(PeriodStates.FINAL_DISCUSSION);
        }

        @Override
        public boolean isNonPassable(PeriodStates period) {
            return period.equals(PeriodStates.FIRST_DISCUSSION)
                    || period.equals(PeriodStates.FINAL_DISCUSSION);
        }
    },
    FIRST_FAILED("first-failed") {
        @Override
        public PassStates pass(PeriodStates period) {
            if (isPassable(period)) return PassStates.FIRST_PASSED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public PassStates nonPass(PeriodStates period) {
            throw NotOperatedException.EXCEPTION;
        }

        @Override
        public boolean isPassable(PeriodStates period) {
            return period.equals(PeriodStates.FIRST_DISCUSSION);
        }

        @Override
        public boolean isNonPassable(PeriodStates period) {
            return false;
        }
    },
    FINAL_PASSED("final-passed") {
        @Override
        public PassStates pass(PeriodStates period) {
            throw NotOperatedException.EXCEPTION;
        }

        @Override
        public PassStates nonPass(PeriodStates period) {
            if (isNonPassable(period)) return PassStates.FINAL_FAILED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public boolean isPassable(PeriodStates period) {
            return false;
        }

        @Override
        public boolean isNonPassable(PeriodStates period) {
            return period.equals(PeriodStates.FINAL_DISCUSSION);
        }
    },
    FINAL_FAILED("final-failed") {
        @Override
        public PassStates pass(PeriodStates period) {
            if (isPassable(period)) return PassStates.FINAL_PASSED;
            else throw NotOperatedException.EXCEPTION;
        }

        @Override
        public PassStates nonPass(PeriodStates period) {
            throw NotOperatedException.EXCEPTION;
        }

        @Override
        public boolean isPassable(PeriodStates period) {
            return period.equals(PeriodStates.FINAL_DISCUSSION);
        }

        @Override
        public boolean isNonPassable(PeriodStates period) {
            return false;
        }
    };

    private final String state;

    PassStates(String state) {
        this.state = state;
    }

    public abstract PassStates pass(PeriodStates period);

    public abstract PassStates nonPass(PeriodStates period);

    public abstract boolean isPassable(PeriodStates period);

    public abstract boolean isNonPassable(PeriodStates period);

    public static PassStates findStatus(String state) {
        return Arrays.stream(PassStates.values())
                .filter(s -> s.getState().equals(state))
                .findFirst()
                .orElseThrow(ApplicantWrongStateException::new);
    }

    @Override
    public String toString() {
        if (this == FIRST_FAILED || this == FINAL_FAILED) return "non-passed";
        return this.state;
    }
}
