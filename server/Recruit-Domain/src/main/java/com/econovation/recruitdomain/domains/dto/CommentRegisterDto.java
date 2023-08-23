package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CommentRegisterDto {
    private String content;
    private Long parentId;
    private UUID applicantId;
    private Long idpId;

    public static Comment from(CommentRegisterDto commentRegisterDto) {
        return Comment.builder()
                .content(commentRegisterDto.getContent())
                .parentId(commentRegisterDto.getParentId())
                .applicantId(commentRegisterDto.getApplicantId())
                .idpId(commentRegisterDto.getIdpId())
                .build();
    }
}
