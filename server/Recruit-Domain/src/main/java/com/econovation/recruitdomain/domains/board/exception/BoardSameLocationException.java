package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class BoardSameLocationException extends RecruitCodeException {
    public static final BoardSameLocationException EXCEPTION = new BoardSameLocationException();

    private BoardSameLocationException() {
        super(BoardErrorCode.BOARD_SAME_LOCATION);
    }
}
