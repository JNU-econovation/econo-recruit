import { FC } from "react";
import { ApplicantCommentReq } from "@/src/apis/applicant/comment";
import { Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";

type ApplicantCommentDetailProps = {
  comment: ApplicantCommentReq;
};

const ApplicantCommentDetail: FC<ApplicantCommentDetailProps> = ({
  comment,
}) => {
  const { content, createAt, interviewerName, isLike, likeCount, canEdit } =
    comment;
  return (
    <div className="border-l-4 border-[#717171] pl-3">
      <div className="flex justify-between mb-4">
        <div className="flex gap-4 items-end">
          <div>{interviewerName}</div>
          <div className="text-xs">
            {new Date(createAt).toLocaleDateString()}
          </div>
        </div>
        <div className="flex gap-2 items-end">
          <img
            src={
              isLike
                ? "/icons/face.smiling.fill.svg"
                : "/icons/face.smiling.svg"
            }
            alt=""
          />
          <span className="text-xs text-[#808080]">{likeCount}</span>
        </div>
      </div>
      <Viewer
        className="text-sm"
        initialEditType="markdown"
        initialValue={content || ""}
      />
      {canEdit && (
        <div className="flex text-sm gap-2 text-[#666666] items-center">
          <button>수정</button>
          <div className="border-x-[0.5px] h-4 !w-0 border-[#666666]"></div>
          <button>삭제</button>
        </div>
      )}
    </div>
  );
};

export default ApplicantCommentDetail;
