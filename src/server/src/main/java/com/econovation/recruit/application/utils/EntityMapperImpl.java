package com.econovation.recruit.application.utils;

import com.econovation.recruit.application.port.out.ApplicantLoadPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.*;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.interviewer.Role;
import com.econovation.recruit.domain.resume.Resume;
import com.econovation.recruit.domain.timetable.TimeTable;
import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityMapperImpl implements EntityMapper {
    private final ApplicantLoadPort applicantLoadPort;
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
                .semester(applicantRegisterDto.getSemester())
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

    @Override
    public Comment CommentRegisterDtoToEntity(CommentRegisterDto commentRegisterDto) {
        Applicant applicant = applicantLoadPort.loadApplicantById(commentRegisterDto.getApplicantId());
        return Comment.builder()
                .applicant(applicant)
                .content(commentRegisterDto.getContent())
                .isDeleted(false)
                .parentId(commentRegisterDto.getParentId())
                .build();
    }

    @Override
    public List<TimeTable> toTimeTables(List<TimeTableInsertDto> timetable, Integer applicantId) {
        List<TimeTable> times = new LinkedList();
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        for (TimeTableInsertDto time:timetable) {
            TimeTable a = TimeTable.builder()
                    .endTime(time.getEndTime())
                    .startTime(time.getStartTime())
                    .day(time.getDay())
                    .applicant(applicant)
                    .build();
            times.add(a);
        }
        return times;
    }

    @Override
    public Comment toComment(CommentRegisterDto commentRegisterDto) {
        Applicant applicant = applicantLoadPort.loadApplicantById(commentRegisterDto.getApplicantId());

        return Comment.builder()
                .parentId(commentRegisterDto.getParentId())
                .idpId(commentRegisterDto.getIdpId())
                .applicant(applicant)
                .content(commentRegisterDto.getContent())
                .build();
    }

    @Override
    public List<Resume> toResumes(List<ResumeInsertDto> resumesDto) {
        Applicant applicant = applicantLoadPort.loadApplicantById(resumesDto.get(0).getApplicantId());
        List<Resume> resumes = new LinkedList();
        for (ResumeInsertDto resume : resumesDto) {
            resumes.add(Resume.builder()
                    .answer(resume.getAnswer())
                    .questionId(resume.getQuestionId())
                    .applicant(applicant)
                    .build());
        }
        return resumes;
    }

    @Override
    public List<Interviewer> toInterviewers(List<InterviewerCreateDto> interviewerCreateDtos) {
        List<Interviewer> interviewers = new LinkedList();
        for(InterviewerCreateDto interviewerCreateDto: interviewerCreateDtos) {
            interviewers.add(Interviewer.builder()
                    .role(Enum.valueOf(Role.class, interviewerCreateDto.getRole()))
                    .id(interviewerCreateDto.getIdpId())
                    .build()
            );
        }
        return interviewers;
    }

    /*@Override
    public CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .
        .build()
    }*/
}
