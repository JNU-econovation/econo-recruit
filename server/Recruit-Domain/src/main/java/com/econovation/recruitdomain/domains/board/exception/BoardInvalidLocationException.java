package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class BoardInvalidLocationException extends RecruitCodeException {
    public static final BoardInvalidLocationException EXCEPTION =
            new BoardInvalidLocationException();

    private BoardInvalidLocationException() {
        super(BoardErrorCode.BOARD_INVALID_LOCATION);
    }
}
