package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import lombok.Getter;

@Getter
public class NavigationNotFoundException extends RecruitCodeException {
    public static final NavigationNotFoundException EXCEPTION = new NavigationNotFoundException();

    private NavigationNotFoundException() {
        super(NavigationErrorCode.NAVIGATION_NOT_FOUND);
    }
}
