import { FC } from "react";
import Txt from "./Txt.component";

interface BoardProps {
  // TODO: Add props
  link: string;
}

const Board: FC<BoardProps> = ({}) => {
  const { title, subElements, time } = {
    title: "title",
    subElements: ["APP", "WEB", "1학년 1학기"],
    time: "2023.03.31",
  };
  return (
    <section>
      <Txt>{title}</Txt>
    </section>
  );
};

export default Board;
