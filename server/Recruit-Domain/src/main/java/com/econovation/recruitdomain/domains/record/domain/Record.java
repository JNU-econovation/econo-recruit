package com.econovation.recruitdomain.domains.record.domain;

import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Integer id;

    @Column(name = "applicant_id")
    private UUID applicantId;

    @Column(name = "url")
    private String url;

    // Long text 제한을 해제
    @Column(name = "record", columnDefinition = "TEXT")
    private String record;
}
