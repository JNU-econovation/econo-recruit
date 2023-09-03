package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentPairVo {
    // commentId
    private Long id;
    private LocalDateTime createdAt;
    private String interviewerName;
    private String content;
    private Boolean isLike;
    private Integer likeCount;
    private Boolean canEdit;

    public static CommentPairVo of(Comment comment, Boolean isLike, String interviewerName, Boolean canEdit) {
        return CommentPairVo.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .content(comment.getContent())
                .isLike(isLike)
                .likeCount(comment.getLikeCount())
                .interviewerName(interviewerName)
                .build();
    }
}
