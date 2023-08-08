package com.econovation.recruit.application.utils;


import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.board.Board;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.dto.*;
import com.econovation.recruitdomain.domains.interviewer.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.Role;
import com.econovation.recruitdomain.domains.record.Record;
import com.econovation.recruitdomain.domains.score.Score;
import com.econovation.recruitdomain.domains.timetable.TimeTable;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private static final String NO_MATCH_SCORE = "해당하는 SCORE가 없습니다";

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

    public BoardResponseDto UpdateLocationBoardDtoToEntity(Board board) {
        return BoardResponseDto.builder().build();
    }

    public Comment CommentRegisterDtoToEntity(
            CommentRegisterDto commentRegisterDto, Applicant applicant) {
        return Comment.builder()
                .applicant(applicant)
                .content(commentRegisterDto.getContent())
                .isDeleted(false)
                .parentId(commentRegisterDto.getParentId())
                .build();
    }

    public List<TimeTable> toTimeTables(List<TimeTableInsertDto> timetable, Applicant applicant) {
        List<TimeTable> times = new LinkedList();
        for (TimeTableInsertDto time : timetable) {
            TimeTable a =
                    TimeTable.builder()
                            .endTime(time.getEndTime())
                            .startTime(time.getStartTime())
                            .day(time.getDay())
                            .applicant(applicant)
                            .build();
            times.add(a);
        }
        return times;
    }

    public Comment toComment(CommentRegisterDto commentRegisterDto, Applicant applicant) {
        return Comment.builder()
                .parentId(commentRegisterDto.getParentId())
                .idpId(commentRegisterDto.getIdpId())
                .applicant(applicant)
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

    public Score toScore(CreateScoreDto createScoreDto, Applicant applicant) {
        return Score.builder()
                .criteria(createScoreDto.getCriteria())
                .score(createScoreDto.getScore())
                .applicant(applicant)
                .idpId(createScoreDto.getIdpId())
                .build();
    }

    public Score toScore(UpdateScoreDto updateScoreDto, Applicant applicant) {
        return Score.builder()
                .score(updateScoreDto.getScore())
                .criteria(updateScoreDto.getCriteria())
                .applicant(applicant)
                .build();
    }

    public Record toRecord(CreateRecordDto createRecordDto, Applicant applicant) {
        return Record.builder()
                .url(createRecordDto.getUrl())
                .record(createRecordDto.getRecord())
                .applicant(applicant)
                .build();
    }
}
