import { useState } from "react";
import InterviewCommentCellComponent from "./CommentCell.component";
import { InterviewCommentMock } from "@/mock/MockData";

const InterviewCommentComponent = () => {
  const [isOpen, setIsOpen] = useState(false);
  const items = InterviewCommentMock;
  return (
    <div className="flex flex-col w-full my-10 items-end">
      <button onClick={() => setIsOpen((prev) => !prev)}>
        <span className="text-sm text-[#A7A7A7] underline underline-offset-2">
          {isOpen ? "접어 두기" : "상세 보기"}
        </span>
      </button>
      {isOpen ? (
        <div className="flex flex-col w-full gap-6 mt-8">
          {items.map((item) => (
            <InterviewCommentCellComponent item={item} key={item.name} />
          ))}
        </div>
      ) : (
        <></>
      )}
    </div>
  );
};
export default InterviewCommentComponent;
