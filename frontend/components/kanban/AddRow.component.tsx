"use client";

import { useState } from "react";

type KanbanAddRowComponent = {
  AddRowCallBack: () => void;
};

function KanbanAddRowComponent({ AddRowCallBack }: KanbanAddRowComponent) {
  const [isOpenAddCard, setIsOpenAddCard] = useState(false);

  return (
    <div className="w-[17rem]">
      {isOpenAddCard ? (
        <div className="w-[17rem] border-[1px] border-[#F0F0F0] p-3 rounded-lg">
          <input
            type="text"
            className="p-3 border-[1px] border-[#F0F0F0] bg-white rounded-lg w-full text-sm my-3 outline-none"
            placeholder="Enter list title"
          />
          <div className="flex gap-3 justify-end">
            <button type="button" onClick={() => setIsOpenAddCard(false)}>
              <img src="/icons/ellipsis.multiply.svg" alt="" />
            </button>
            <button type="submit" onClick={AddRowCallBack}>
              <img src="/icons/arrow.forward.circle.fill.svg" alt="" />
            </button>
          </div>
        </div>
      ) : (
        <button
          type="button"
          className="w-[17rem] flex gap-6 justify-start items-center text-[#828282] py-4 px-6 bg-[#EFEFEF] h-fit rounded-lg text-lg"
          onClick={() => setIsOpenAddCard(true)}
        >
          <img src="/icons/ellipsis.plus.svg" alt="AddColumn" />
          Add another list
        </button>
      )}
    </div>
  );
}

export default KanbanAddRowComponent;
