package com.econovation.recruitdomain.domains.record.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer id;

    @Column(name = "applicant_id")
    private String applicantId;

    @Column(name = "url")
    private String url;

    // Long text 제한을 해제
    @Column(name = "record", columnDefinition = "TEXT")
    private String record;

    public void updateUrl(String url) {
        this.url = url;
    }

    public void updateRecord(String contents) {
        this.record = contents;
    }
}
