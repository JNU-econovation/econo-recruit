package com.econovation.recruitdomain.domains.comment.exception;

import static com.econovation.recruitcommon.consts.RecruitStatic.BAD_REQUEST;
import static com.econovation.recruitcommon.consts.RecruitStatic.NOT_FOUND;

import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {
    COMMENT_DUPLICATE_CREATED(BAD_REQUEST, "COMMENT_400_1", "중복된 댓글를 생성했습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404_1", "존재하지 않는 댓글입니다."),
    COMMENT_NOT_HOST(BAD_REQUEST, "COMMENT_400_2", "댓글의 작성자가 아닙니다."),
    COMMENT_NOT_APPLICANT(BAD_REQUEST, "COMMENT_400_3", "댓글이 지원서 댓글이 아닙니다."),
    COMMENT_INVALID_CREATED(
            BAD_REQUEST, "COMMENT_400_4", "댓글을 생성할 수 없습니다.(cardId, applicantId 둘 중 하나는 필수입니다.)"),
    ;
    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
