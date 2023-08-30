import { FC } from "react";
import Txt from "../Txt.component";

export interface BoardCellProps {
  title: string;
  subElements: string[];
  score?: string;
  time: Date;
  onClick?: () => void;
}

const BoardCell: FC<BoardCellProps> = ({
  title,
  subElements,
  time,
  score,
  onClick,
}) => {
  return (
    <button className="flex border-t py-4 justify-between" onClick={onClick}>
      <Txt typography="h6">{title}</Txt>
      <div className="flex gap-20">
        {subElements.map((subElement) => (
          <Txt key="" typography="p" color="light_gray">
            {subElement}
          </Txt>
        ))}
        {score && <Txt typography="p">{score}</Txt>}
        <Txt typography="p" color="light_gray">
          {time.toLocaleDateString()}
        </Txt>
      </div>
    </button>
  );
};

export default BoardCell;
