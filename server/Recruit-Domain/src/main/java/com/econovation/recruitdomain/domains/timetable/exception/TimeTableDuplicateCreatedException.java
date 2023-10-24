package com.econovation.recruitdomain.domains.timetable.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class TimeTableDuplicateCreatedException extends RecruitCodeException {
    public static final TimeTableDuplicateCreatedException EXCEPTION =
            new TimeTableDuplicateCreatedException();

    private TimeTableDuplicateCreatedException() {
        super(TimeTableErrorCode.TIMETABLE_DUPLICATE_SUBMIT);
    }
}
