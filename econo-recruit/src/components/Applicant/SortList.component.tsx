import { useEffect, useRef, useState } from 'react';

type ApplicantSortListComponent = {
  sortList: { type: string; string: string }[];
};

const ApplicantSortListComponent = ({
  sortList,
}: ApplicantSortListComponent) => {
  const [order, setOrder] = useState(sortList[0].type);
  const [isOpen, setIsOpen] = useState(false);
  // const modelRef = useRef<HTMLDivElement>(null);

  // useEffect(() => {
  //   const clickModalOutside = (event: MouseEvent) => {
  //     if (modelRef.current && !modelRef.current.contains(event.target as any)) {
  //       setIsOpen(false);
  //     }
  //   };
  //   document.addEventListener('click', clickModalOutside);
  //   return document.removeEventListener('click', clickModalOutside);
  // });

  return (
    <div className="relative z-10">
      <button
        className="flex items-center bg-[#F9FBFF] p-4 rounded-2xl  gap-[2px] text-[#7E7E7E]"
        onClick={(e) => {
          e.preventDefault();
          setIsOpen(!isOpen);
        }}
      >
        Sort by<span>:</span>
        <span className="font-bold capitalize text-[#3D3C42]">{order}</span>
        <img src="/chevron-down.svg" alt="dowp_down" />
      </button>
      {isOpen ? (
        <div
          className="flex flex-col absolute w-full border-[#F0F0F0] rounded-xl border-[1px] bg-white p-6 font-bold -mt-4 -z-10 text-[#3D3C42]"
          // ref={modelRef}
        >
          {sortList.map((sort) => (
            <div
              className="flex justify-end cursor-pointer py-2 px-6 capitalize"
              onClick={() => {
                setOrder(sort.type);
                setIsOpen(false);
              }}
              key={sort.type}
            >
              {sort.type}
            </div>
          ))}
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ApplicantSortListComponent;
