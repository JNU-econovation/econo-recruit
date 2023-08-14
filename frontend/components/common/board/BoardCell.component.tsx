import { FC } from "react";
import Txt from "../Txt.component";

export interface BoardCellProps {
  title: string;
  subElements: string[];
  time: Date;
  onClick?: () => void;
}

const BoardCell: FC<BoardCellProps> = ({
  title,
  subElements,
  time,
  onClick,
}) => {
  return (
    <button className="border-t py-4" onClick={onClick}>
      <Txt typography="h6">{title}</Txt>
    </button>
  );
};

export default BoardCell;
