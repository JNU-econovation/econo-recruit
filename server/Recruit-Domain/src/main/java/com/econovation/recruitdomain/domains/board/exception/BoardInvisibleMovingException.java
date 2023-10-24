package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import lombok.Getter;

@Getter
public class BoardInvisibleMovingException extends RecruitCodeException {
    public static final BoardInvisibleMovingException EXCEPTION =
            new BoardInvisibleMovingException();

    private BoardInvisibleMovingException() {
        super(BoardErrorCode.INVISIBLE_MOVING);
    }
}
