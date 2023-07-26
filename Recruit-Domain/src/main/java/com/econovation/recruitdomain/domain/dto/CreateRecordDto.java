package com.econovation.recruitdomain.domain.dto;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateRecordDto {
    private Integer applicantId;
    private String url;
    private String record;
}
