import { FC } from "react";
import Txt from "../Txt.component";

export interface BoardCellProps {
  title: string;
  subElements: string[];
  time: Date;
}

const BoardCell: FC<BoardCellProps> = ({ title, subElements, time }) => {
  return (
    <>
      <Txt>{title}</Txt>
    </>
  );
};

export default BoardCell;
