package com.econovation.recruitdomain.domains.dto;

import javax.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CommentRegisterDto {
    private String content;
    private Long parentCommentId;
    @Nullable private String applicantId;
    @Nullable private Long cardId;
}
