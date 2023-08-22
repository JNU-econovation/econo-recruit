

package com.econovation.recruitdomain.domains.interviewer.exception;


import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerNotFoundException extends RecruitCodeException {
    public static final InterviewerNotFoundException EXCEPTION = new InterviewerNotFoundException();
    private InterviewerNotFoundException() {super(InterviewerErrorCode.INTERVIEWER_NOT_FOUND);}
}
