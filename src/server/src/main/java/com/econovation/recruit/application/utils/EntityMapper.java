package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.*;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.record.Record;
import com.econovation.recruit.domain.resume.Resume;
import com.econovation.recruit.domain.score.Score;
import com.econovation.recruit.domain.timetable.TimeTable;

import java.util.List;

public interface EntityMapper {
    Applicant toApplicant(ApplicantRegisterDto applicantRegisterDto);
    BoardResponseDto UpdateLocationBoardDtoToEntity(Board board);
    Comment CommentRegisterDtoToEntity(CommentRegisterDto commentRegisterDto);
    List<TimeTable> toTimeTables(List<TimeTableInsertDto> timetable, Integer applicantId);

    Comment toComment(CommentRegisterDto commentRegisterDto);

    List<Resume> toResumes(List<ResumeInsertDto> resumesDto);

    List<Interviewer> toInterviewers(List<InterviewerCreateDto> interviewerCreateDto);

    Score toScore(CreateScoreDto createScoreDto);

    Score toScoreByUpdateDto(UpdateScoreDto updateScoreDto);

    Record toRecord(CreateRecordDto createRecordDto);

    //    CommentResponseDto toCommentResponseDto(Comment comment);

}
