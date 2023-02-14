package com.econovation.recruit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long parentId;
    private Integer applicantId;
}
