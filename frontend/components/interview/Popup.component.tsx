import { interviewPopupBooleanState } from "@/src/stores/interview/Interview.atom";
import { useAtom } from "jotai";
import InterviewUserComponent from "./User.component";
import InterviewLabelComponent from "./Label.component";
import InterviewCommentComponent from "./Comment.component";
import { InterviewRecode } from "@/mock/MockData";

const InterviewPopupComponent = () => {
  const [isPopuped, setIsPopuped] = useAtom(interviewPopupBooleanState);
  return isPopuped ? (
    <div className="flex justify-center z-20 bg-[rgba(255,255,255,0.8)] absolute top-0 left-0 min-w-fit w-full max-h-fit overflow-hidden">
      <div className="max-w-[95rem] h-full p-20 drop-shadow-[0_2px_2px_rgba(0,0,0,0.25)]">
        <div className="flex flex-col bg-white p-16">
          <button onClick={() => setIsPopuped(false)}>
            <img
              className="w-4"
              src="/icons/ellipsis.multiply.svg"
              alt="close popup"
            ></img>
          </button>
          <div className="flex flex-1 mt-10">
            <div className="w-[36rem] h-fit overflow-hidden mr-[4rem]">
              <InterviewUserComponent src="." />
              <InterviewLabelComponent />
              <InterviewCommentComponent />
            </div>
            <div className="flex-1 min-w-[30rem] pl-10 border-l-2">
              <div className="p-2 overflow-auto max-h-[47rem]">
                <pre className="whitespace-pre-wrap">{InterviewRecode}</pre>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  ) : (
    <></>
  );
};

export default InterviewPopupComponent;
