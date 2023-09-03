"use client";

import { FC } from "react";
import ApplicantCommentInputForm from "./InputForm.component";
import { useQuery } from "@tanstack/react-query";
import { getAllCommentById } from "@/src/apis/comment/comment";
import ApplicantCommentDetail from "./CommentDetail.component";

interface ApplicantCommentProps {
  postId: string;
}

const ApplicantComment: FC<ApplicantCommentProps> = ({ postId }) => {
  const { data, error, isLoading } = useQuery(
    ["applicantComment", postId],
    () => getAllCommentById(postId),
    {
      enabled: !!postId,
    }
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
      />
      <div className="flex flex-col gap-8 pt-8">
        {data.map((comment) => (
          <ApplicantCommentDetail comment={comment} key={comment.id} />
        ))}
      </div>
    </>
  );
};

export default ApplicantComment;
