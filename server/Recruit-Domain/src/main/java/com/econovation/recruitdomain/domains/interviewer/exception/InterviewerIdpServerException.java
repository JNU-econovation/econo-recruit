

package com.econovation.recruitdomain.domains.interviewer.exception;


import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerIdpServerException extends RecruitCodeException {
    public static final InterviewerIdpServerException EXCEPTION = new InterviewerIdpServerException();
    private InterviewerIdpServerException() {super(InterviewerErrorCode.INTERVIEWER_NOT_FOUND);}
}