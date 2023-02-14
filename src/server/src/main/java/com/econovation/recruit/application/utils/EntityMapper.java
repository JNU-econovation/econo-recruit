package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;
import com.econovation.recruit.domain.dto.BoardResponseDto;
import com.econovation.recruit.domain.dto.CommentResponseDto;

public interface EntityMapper {
    Applicant toApplicant(ApplicantRegisterDto applicantRegisterDto);

    BoardResponseDto UpdateLocationBoardDtoToEntity(Board board);

//    CommentResponseDto toCommentResponseDto(Comment comment);
}
