package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;
import com.econovation.recruit.domain.dto.BoardResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapperImpl implements EntityMapper{
    @Override
    public Applicant toApplicant(ApplicantRegisterDto applicantRegisterDto) {
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
                .semester(Integer.valueOf(applicantRegisterDto.getSemester()))
                .studentId(applicantRegisterDto.getStudentId())
                .phoneNumber(applicantRegisterDto.getPhoneNumber())
                .name(applicantRegisterDto.getName())
                .secondPriority(applicantRegisterDto.getSecondPriority())
                .portfolio((applicantRegisterDto.getPortfolio()))
                .supportPath(applicantRegisterDto.getSupportPath())
                .plan(applicantRegisterDto.getPlan())
                .build();
    }

    @Override
    public BoardResponseDto UpdateLocationBoardDtoToEntity(Board board) {
        return BoardResponseDto.builder().
                build();
    }

    /*@Override
    public CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .
        .build()
    }*/
}
