package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
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



    public static Comment from(CommentRegisterDto commentRegisterDto) {
        return Comment.builder()
                .content(commentRegisterDto.getContent())
                .parentId(commentRegisterDto.getParentCommentId())
                .applicantId(commentRegisterDto.getApplicantId())
                .cardId(commentRegisterDto.getCardId())
                .build();
    }
}
