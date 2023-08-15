"use client";

import { FC, PropsWithChildren, useState } from "react";
import BoardCell from "./BoardCell.component";
import Modal from "react-modal";
import Image from "next/image";
import CloseImage from "/public/icons/ellipsis.multiply.svg";

interface BoardProps {
  baseUrl: string;
  onClick?: () => void;
}

const Board: FC<PropsWithChildren<BoardProps>> = ({
  baseUrl,
  children,
  onClick,
}) => {
  const [isOpen, setIsOpen] = useState(false);

  const openModel = () => {
    setIsOpen(true);
    onClick && onClick();
  };
  const closeModel = () => setIsOpen(false);

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
          onClick={openModel}
        />
      ))}
      <Modal isOpen={isOpen} onRequestClose={closeModel} ariaHideApp={false}>
        <div className="relative">
          <button className="absoltue" onClick={closeModel}>
            <Image src={CloseImage} alt="close" />
          </button>
          <div className="w-full h-full">{children}</div>
        </div>
      </Modal>
    </section>
  );
};

export default Board;
