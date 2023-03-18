package com.econovation.recruit.application.utils;

import com.econovation.recruit.application.port.out.ApplicantLoadPort;
import com.econovation.recruit.application.port.out.ScoreLoadPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.*;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.interviewer.Role;
import com.econovation.recruit.domain.record.Record;
import com.econovation.recruit.domain.resume.Resume;
import com.econovation.recruit.domain.score.Score;
import com.econovation.recruit.domain.score.ScoreRepository;
import com.econovation.recruit.domain.timetable.TimeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityMapperImpl implements EntityMapper {
    private static final String NO_MATCH_SCORE = "해당하는 SCORE가 없습니다";
    private final ScoreRepository scoreRepository;
    private final ApplicantLoadPort applicantLoadPort;
    private final ScoreLoadPort scoreLoadPort;
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
                    .role(Role.getByName(interviewerCreateDto.getRole()))
                    .id(interviewerCreateDto.getIdpId())
                    .build()
            );
        }
        return interviewers;
    }

    @Override
    public Score toScore(CreateScoreDto createScoreDto) {
        Applicant applicant = applicantLoadPort.loadApplicantById(createScoreDto.getApplicantId());
        return Score.builder()
                .criteria(createScoreDto.getCriteria())
                .score(createScoreDto.getScore())
                .applicant(applicant)
                .idpId(createScoreDto.getIdpId())
                .build();
    }

    @Override
    public Score toScoreByUpdateDto(UpdateScoreDto updateScoreDto) {
        Score score = scoreRepository.findById(updateScoreDto.getScoreId()).orElseThrow(() -> new IllegalArgumentException(NO_MATCH_SCORE));
        return score.updateScore(updateScoreDto.getCriteria(), updateScoreDto.getScore());
    }

    @Override
    public Record toRecord(CreateRecordDto createRecordDto) {
        Applicant applicant = applicantLoadPort.loadApplicantById(createRecordDto.getApplicantId());
        return Record.builder()
                .url(createRecordDto.getUrl())
                .record(createRecordDto.getRecord())
                .applicant(applicant)
                .build();
    }

    /*@Override
    public CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .
        .build()
    }*/
}
