package com.econovation.recruit.api.applicant.dto;


public record ApplicantStateResponse(String passState, boolean isPassable, boolean isNonPassable) {

    public static ApplicantStateResponse of(String passState, boolean isPassable, boolean isNonPassable) {
        return new ApplicantStateResponse(passState, isPassable, isNonPassable);
    }
}
