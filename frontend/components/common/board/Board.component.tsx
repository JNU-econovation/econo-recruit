"use client";

import { FC, PropsWithChildren, useState } from "react";
import BoardCell from "./BoardCell.component";
import Modal from "react-modal";
import Image from "next/image";
import CloseImage from "/public/icons/ellipsis.multiply.svg";
import classNames from "classnames";

interface BoardProps {
  onClick?: () => void;
  wapperClassname?: string;
}

const Board: FC<PropsWithChildren<BoardProps>> = ({
  children,
  onClick,
  wapperClassname,
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
      <Modal
        style={{
          content: {
            width: "calc(100% - 12rem)",
            height: "calc(100%)",
            margin: "3rem 6rem 0 6rem",
            minWidth: "1280px",
            boxShadow: "0px 0px 6px 1px rgba(0, 0, 0, 0.14)",
            border: "none",
            position: "relative",
            inset: "0",
            padding: "2.5rem 3rem",
          },
          overlay: {
            padding: "0",
          },
        }}
        isOpen={isOpen}
        onRequestClose={closeModel}
        ariaHideApp={false}
      >
        <button className="fixed z-10" onClick={closeModel}>
          <Image src={CloseImage} alt="close" />
        </button>
        <div
          className={classNames(
            "flex pt-8 absolute h-[calc(100%-6rem)] w-[calc(100%-6rem)]",
            wapperClassname
          )}
        >
          {children}
        </div>
      </Modal>
    </section>
  );
};

export default Board;
