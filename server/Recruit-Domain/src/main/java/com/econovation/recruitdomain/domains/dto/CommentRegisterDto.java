package com.econovation.recruitdomain.domains.dto;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CommentRegisterDto {
    private String content;
    private Long parentId;
    private Integer applicantId;
    private Integer idpId;
}
