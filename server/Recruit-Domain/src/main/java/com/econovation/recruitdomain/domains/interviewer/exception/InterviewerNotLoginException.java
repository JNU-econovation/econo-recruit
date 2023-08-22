

package com.econovation.recruitdomain.domains.interviewer.exception;


import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerNotLoginException extends RecruitCodeException {
    public static final InterviewerNotLoginException EXCEPTION = new InterviewerNotLoginException();
    private InterviewerNotLoginException() {super(InterviewerErrorCode.INTERVIEWER_NOT_LOGIN);}
}
