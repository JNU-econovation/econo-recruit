import { FC } from "react";
import Txt from "../Txt.component";

interface BoardProps {
  // TODO: Add props
  baseUrl: string;
}

const Board: FC<BoardProps> = ({ baseUrl }) => {
  const boardData = [
    {
      title: "title",
      subElements: ["APP", "WEB", "1학년 1학기"],
      time: "2023.03.31",
    },
  ];
  return (
    <section>
      <Txt>{boardData[0].title}</Txt>
    </section>
  );
};

export default Board;
