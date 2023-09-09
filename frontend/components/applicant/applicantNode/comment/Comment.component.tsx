"use client";

import { FC } from "react";
import ApplicantCommentInputForm from "./InputForm.component";
import { useQuery } from "@tanstack/react-query";
import ApplicantCommentDetail from "./CommentDetail.component";
import { getAllCommentById } from "../../../../src/apis/comment/comment";

interface ApplicantCommentProps {
  postId: string;
  generation: string;
}

const ApplicantComment: FC<ApplicantCommentProps> = ({
  postId,
  generation,
}) => {
  const { data, error, isLoading } = useQuery(
    ["applicantComment", postId],
    () => getAllCommentById(postId)
  );

  if (!data || isLoading) {
    return <div>로딩중...</div>;
  }

  if (error) {
    return <div>에러 발생</div>;
  }

  return (
    <>
      <ApplicantCommentInputForm
        applicantId={postId}
        commentLength={data.length}
        generation={generation}
      />
      <div className="flex flex-col gap-8 pt-8">
        {data.map((comment) => (
          <ApplicantCommentDetail
            generation={generation}
            comment={comment}
            postId={postId}
            key={comment.id}
          />
        ))}
      </div>
    </>
  );
};

export default ApplicantComment;
