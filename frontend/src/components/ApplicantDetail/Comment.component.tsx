import { ApplicantCommentMock } from '@/mock/MockData';
import CommentDetailComponent from './CommentDetail.component';
import CommentInputForm from './CommentInputForm.component';

const ApplicantCommentComponent = () => {
  const commentData = ApplicantCommentMock;

  return (
    <div className="w-[28rem]">
      <div className="flex justify-between items-center">
        <div className="flex gap-4 items-center">
          <div className="text-lg font-semibold">댓글</div>
          <div className="text-sm">{commentData.length}개</div>
        </div>
        <button>
          <img src="/arrow.forward.circle.fill.svg" alt="" />
        </button>
      </div>
      <CommentInputForm />
      <div className="flex flex-col gap-8 pt-8">
        {commentData.map((comment) => (
          <CommentDetailComponent comment={comment} key={comment.id} />
        ))}
      </div>
    </div>
  );
};

export default ApplicantCommentComponent;
