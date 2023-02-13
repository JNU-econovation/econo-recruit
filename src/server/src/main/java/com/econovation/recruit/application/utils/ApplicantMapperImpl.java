package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapperImpl {
    Applicant toEntity(ApplicantRegisterDto applicantRegisterDto) {
        return Applicant.builder()
                .email(applicantRegisterDto.getEmail())
                .grade(applicantRegisterDto.getGrade())
//                .card()
                .major(applicantRegisterDto.getMajor())
                .minor(applicantRegisterDto.getMinor())
                .doubleMajor(applicantRegisterDto.getDoubleMajor())
                .firstPriority(applicantRegisterDto.getFirstPriority())
                .hopeField(applicantRegisterDto.getHopeField())
//                .commentCount()
//                .likeCount()
                .semester(applicantRegisterDto.getSemester())
                .studentId(applicantRegisterDto.getStudentId())
                .phoneNumber(applicantRegisterDto.getPhoneNumber())
                .name(applicantRegisterDto.getName())
                .secondPriority(applicantRegisterDto.getSecondPriority())
                .build();
    }
}
