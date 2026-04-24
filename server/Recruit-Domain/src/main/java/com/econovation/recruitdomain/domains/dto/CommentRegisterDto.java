package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Data
@Getter
public class CommentRegisterDto {
    private String content;
    private Long parentCommentId;
    @Nullable private String applicantId;
    @Nullable private Long cardId;
}
