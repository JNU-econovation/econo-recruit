package com.econovation.recruitdomain.domains.timetable.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class TimeTableNotFoundException extends RecruitCodeException {
    public static final TimeTableNotFoundException EXCEPTION = new TimeTableNotFoundException();

    private TimeTableNotFoundException() {
        super(TimeTableErrorCode.TIMETABLE_NOT_FOUND);
    }
}
