package com.econovation.recruit.application.utils;

import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.dto.*;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.domains.record.Record;
import com.econovation.recruitdomain.domains.score.domain.Score;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private static final String NO_MATCH_SCORE = "해당하는 SCORE가 없습니다";

    /*    public Applicant toApplicant(ApplicantRegisterDto applicantRegisterDto) {
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
                .supportPath(applicantRegisterDto.getSupportPath())
                .build();
    }*/

    public BoardResponseDto UpdateLocationBoardDtoToEntity(Board board) {
        return BoardResponseDto.builder().build();
    }

    public Comment CommentRegisterDtoToEntity(CommentRegisterDto commentRegisterDto) {
        return Comment.builder()
                .applicantId(commentRegisterDto.getApplicantId())
                .content(commentRegisterDto.getContent())
                .isDeleted(false)
                .parentId(commentRegisterDto.getParentId())
                .build();
    }

    public List<TimeTable> toTimeTables(List<TimeTableInsertDto> timetable, UUID applicantId) {
        List<TimeTable> times = new LinkedList();
        for (TimeTableInsertDto time : timetable) {
            TimeTable a =
                    TimeTable.builder()
                            .startTime(time.getStartTime())
                            .applicantId(applicantId)
                            .build();
            times.add(a);
        }
        return times;
    }

    public Comment toComment(CommentRegisterDto commentRegisterDto) {
        return Comment.builder()
                .parentId(commentRegisterDto.getParentId())
                .idpId(commentRegisterDto.getIdpId())
                .applicantId(commentRegisterDto.getApplicantId())
                .content(commentRegisterDto.getContent())
                .build();
    }

    //    public List<Resume> toResumes(List<ResumeInsertDto> resumesDto,appl) {
    //        List<Resume> resumes = new LinkedList();
    //        for (ResumeInsertDto resume : resumesDto) {
    //            resumes.add(
    //                    Resume.builder()
    //                            .answer(resume.getAnswer())
    //                            .questionId(resume.getQuestionId())
    //                            .applicant(applicant)
    //                            .build());
    //        }
    //        return resumes;
    //    }

    public List<Interviewer> toInterviewers(List<InterviewerCreateDto> interviewerCreateDtos) {
        List<Interviewer> interviewers = new LinkedList();
        for (InterviewerCreateDto interviewerCreateDto : interviewerCreateDtos) {
            interviewers.add(
                    Interviewer.builder()
                            .role(Role.getByName(interviewerCreateDto.getRole()))
                            .id(interviewerCreateDto.getIdpId())
                            .build());
        }
        return interviewers;
    }

    public Score toScore(CreateScoreDto createScoreDto) {
        return Score.builder()
                .criteria(createScoreDto.getCriteria())
                .score(createScoreDto.getScore())
                .applicantId(createScoreDto.getApplicantId())
                .idpId(createScoreDto.getIdpId())
                .build();
    }

    public Score toScore(UpdateScoreDto updateScoreDto) {
        return Score.builder()
                .score(updateScoreDto.getScore())
                .criteria(updateScoreDto.getCriteria())
                .applicantId(updateScoreDto.getApplicantId())
                .build();
    }

    public Record toRecord(CreateRecordDto createRecordDto) {
        return Record.builder()
                .url(createRecordDto.getUrl())
                .record(createRecordDto.getRecord())
                .applicantId(createRecordDto.getApplicantId())
                .build();
    }
}
