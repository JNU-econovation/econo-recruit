import { useState } from 'react'
import { ApplicantCommentMock } from '../../Mock/MockData'
import CommentDetailElement from './CommentDetail.element'

const ApplicantCommentElement = () => {
  const [isNocomment, setIsNocomment] = useState(false)
  const commentData = ApplicantCommentMock

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
      <div>
        <textarea
          className="w-full my-4 border-[1px] rounded border-[#DBDBDB] p-3 outline-none resize-none text-sm h-24"
          disabled={isNocomment}
        ></textarea>
        <div className="font-normal">
          <div className="flex items-center gap-2 text-sm font-normal my-2">
            <input className="accent-black" type="checkbox" name="question" id="question" />
            <label htmlFor="question">질문드립니다.</label>
          </div>
          <div className="flex items-center gap-2 text-sm font-normal my-2">
            <input
              className="accent-black"
              type="checkbox"
              name="nocomment"
              id="nocomment"
              checked={isNocomment}
              onChange={() => setIsNocomment(!isNocomment)}
            />
            <label htmlFor="nocomment">지인이므로 코멘트 삼가겠습니다.</label>
          </div>
        </div>
      </div>
      <div className="flex flex-col gap-8 pt-8">
        {commentData.map((comment) => (
          <CommentDetailElement comment={comment} key={comment.id} />
        ))}
      </div>
    </div>
  )
}

export default ApplicantCommentElement
