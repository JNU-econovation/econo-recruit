package com.econovation.recruit.api.applicant.dto;

public record ApplicantStateResponse(String passState) {

    public static ApplicantStateResponse of(String passState) {
        return new ApplicantStateResponse(passState);
    }
}
