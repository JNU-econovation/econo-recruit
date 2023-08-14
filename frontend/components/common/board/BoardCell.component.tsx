import { FC } from "react";
import Txt from "../Txt.component";

export interface BoardCellProps {
  title: string;
  subElements: string[];
  time: Date;
}

const BoardCell: FC<BoardCellProps> = ({ title, subElements, time }) => {
  return (
    <button className="border-t py-4" onClick={() => {}}>
      <Txt typography="h6">{title}</Txt>
    </button>
  );
};

export default BoardCell;
