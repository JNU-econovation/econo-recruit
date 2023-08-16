"use client";

import { useState } from "react";

type KanbanAddColumnComponent = {
  AddColumnCallBack: () => {};
};

function KanbanAddColumnComponent({
  AddColumnCallBack,
}: KanbanAddColumnComponent) {
  const [isOpenAddCard, setIsOpenAddCard] = useState(false);

  return (
    <div className="mt-2">
      {isOpenAddCard ? (
        <div>
          <input
            type="text"
            className="p-3 border-[1px] border-[#F0F0F0] drop-shadow-md bg-white rounded-lg w-full text-sm my-3 outline-none"
            placeholder="Enter a title for this card"
          />
          <div className="flex gap-3 justify-end">
            <button type="button" onClick={() => setIsOpenAddCard(false)}>
              <img src="/icons/ellipsis.multiply.svg" alt="" />
            </button>
            <button type="submit" onClick={AddColumnCallBack}>
              <img src="/icons/arrow.forward.circle.fill.svg" alt="" />
            </button>
          </div>
        </div>
      ) : (
        <button
          type="button"
          className="flex gap-2 justify-center items-center text-[#828282]"
          onClick={() => {
            setIsOpenAddCard(true);
          }}
        >
          <img src="/icons/ellipsis.plus.svg" alt="AddColumn" />
          Add a card
        </button>
      )}
    </div>
  );
}

export default KanbanAddColumnComponent;
