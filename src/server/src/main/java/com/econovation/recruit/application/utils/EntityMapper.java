package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.*;
import com.econovation.recruit.domain.timetable.TimeTable;

import java.util.List;

public interface EntityMapper {
    Applicant toApplicant(ApplicantRegisterDto applicantRegisterDto);
    BoardResponseDto UpdateLocationBoardDtoToEntity(Board board);
    Comment CommentRegisterDtoToEntity(CommentRegisterDto commentRegisterDto);
    List<TimeTable> toTimeTables(List<TimeTableInsertDto> timetable, Integer applicantId);

    //    CommentResponseDto toCommentResponseDto(Comment comment);

}
