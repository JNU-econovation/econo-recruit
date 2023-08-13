package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import java.util.function.Supplier;
import lombok.Getter;

@Getter
public class BoardNotFoundException extends RecruitCodeException{
    public static final BoardNotFoundException EXCEPTION =
            new BoardNotFoundException();

    private BoardNotFoundException() {
        super(BoardErrorCode.BOARD_NOT_FOUND);
    }
}
