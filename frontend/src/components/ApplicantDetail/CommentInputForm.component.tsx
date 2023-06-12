import { useCallback, useState } from "react";
import InputCheckBox from "../inputCheckBox/InputCheckBox.component";

const CommentInputForm = () => {
  const [isNocomment, setIsNocomment] = useState(false);
  const [comment, setComment] = useState('');
  const onNocommentCheck = useCallback(() => {
    setIsNocomment(!isNocomment);
    setComment(isNocomment ? '' : '지인이므로 코멘트 삼가겠습니다.');
  }, [isNocomment]);

  return (<form>
    <textarea
      className="w-full my-4 border-[1px] rounded border-[#DBDBDB] p-3 outline-none resize-none text-sm h-24"
      disabled={isNocomment}
      value={comment}
      onChange={(e) => setComment(e.target.value)}
    ></textarea>
    <div className="font-normal">
      <InputCheckBox name="question" id="question" title="질문드립니다." />
      <InputCheckBox name="nocomment" id="nocomment" title="지인이므로 코멘트 삼가겠습니다."
        checked={isNocomment} onChange={onNocommentCheck} />
    </div>
  </form>)
}

export default CommentInputForm;