package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentPairVo {
    private Long id;
    private String createdAt;
    private String interviewerName;
    private String content;
    private Boolean isLike;
    private Integer likeCount;
    private Boolean canEdit;

    public static CommentPairVo of(Comment comment, Boolean isLike, String interviewerName, Boolean canEdit) {
        return CommentPairVo.builder()
                .id(comment.getId())
                .createdAt(String.valueOf(Timestamp.valueOf(comment.getCreatedAt()).getTime()))
                .content(comment.getContent())
                .isLike(isLike)
                .likeCount(comment.getLikeCount())
                .interviewerName(interviewerName)
                .canEdit(canEdit)
                .build();
    }
}
