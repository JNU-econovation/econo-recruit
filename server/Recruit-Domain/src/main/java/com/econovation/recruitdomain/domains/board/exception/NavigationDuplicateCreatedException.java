package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class NavigationDuplicateCreatedException extends RecruitCodeException {
    public static final NavigationDuplicateCreatedException EXCEPTION =
            new NavigationDuplicateCreatedException();

    private NavigationDuplicateCreatedException() {
        super(NavigationErrorCode.NAVIGATION_DUPLICATE_CREATED);
    }
}
