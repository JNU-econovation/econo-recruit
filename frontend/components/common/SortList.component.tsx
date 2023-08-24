import { useState } from "react";

type SortListComponent = {
  sortList: { type: string; string: string }[];
  selected: string;
};

const SortListComponent = ({ sortList, selected }: SortListComponent) => {
  const searchParams = new URLSearchParams(location.search);

  const [order, setOrder] = useState(
    searchParams.get("order") || sortList[0].type
  );
  const [isOpen, setIsOpen] = useState(false);
  const buttonClass = "flex justify-end py-2 px-6 capitalize cursor-pointer";

  const handleOrderChange = (order: string) => {
    searchParams.set("order", order);
    location.href = `${location.pathname}?${searchParams.toString()}`;
  };

  return (
    <div className="relative">
      <button
        className="flex items-center bg-[#F9FBFF] p-4 gap-2 rounded-2xl text-[#7E7E7E] z-20"
        onClick={(e) => {
          e.preventDefault();
          setIsOpen((prev) => !prev);
        }}
      >
        Sort by :
        <span className="font-semibold capitalize text-[#3D3C42]">{order}</span>
        <img src="/icons/chevron-down.svg" alt="drop_down"></img>
      </button>
      {isOpen ? (
        <div className="flex flex-col absolute w-full border-[#F0F0F0] rounded-xl border-[1px] bg-white p-6 font-semibold -mt-4 text-[#3D3C42]">
          {sortList.map((sort) => (
            <button
              key={sort.type}
              disabled={sort.type === selected}
              onClick={() => {
                setOrder(sort.type);
                handleOrderChange(sort.type);
                setIsOpen(false);
              }}
              className={
                sort.type === selected
                  ? buttonClass + " text-[#9A9A9A] cursor-auto"
                  : buttonClass
              }
            >
              {sort.type}
            </button>
          ))}
        </div>
      ) : (
        ""
      )}
    </div>
  );
};

export default SortListComponent;
