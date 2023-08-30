import { FC } from "react";
import Txt from "../Txt.component";

export interface BoardCellProps {
  title: string;
  subElements: string[];
  score?: string;
  onClick?: () => void;
}

const BoardCell: FC<BoardCellProps> = ({
  title,
  subElements,
  score,
  onClick,
}) => {
  return (
    <button className="flex border-t py-4 justify-between" onClick={onClick}>
      <Txt typography="h6" className="flex-[2_0_0] text-left">
        {title}
      </Txt>
      <div className="flex gap-20 flex-[2_0_0] text-center">
        {subElements.map((subElement, index) => (
          <Txt
            key={index}
            typography="p"
            color="light_gray"
            className="w-full flex-1 last:text-right"
          >
            {subElement}
          </Txt>
        ))}
        {score && <Txt typography="p">{score}</Txt>}
      </div>
    </button>
  );
};

export default BoardCell;
