"use client";

import { FC } from "react";
import BoardCell from "./BoardCell.component";

interface BoardProps {
  baseUrl: string;
}

const Board: FC<BoardProps> = ({ baseUrl }) => {
  const boardData = Array.from({ length: 10 }).map((_, i) => ({
    id: i,
    title: "[개발자]임채승",
    subElements: ["APP", "WEB", "1학년 1학기"],
    time: new Date(),
  }));

  return (
    <section className="flex flex-col">
      {boardData.map((item, index) => (
        <BoardCell
          key={index}
          title={item.title}
          subElements={item.subElements}
          time={item.time}
        />
      ))}
    </section>
  );
};

export default Board;
