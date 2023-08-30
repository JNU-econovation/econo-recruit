"use client";

import { KanbanColumnData } from "@/src/stores/kanban/Kanban.atoms";
import { useParams } from "next/navigation";

type KanbanColumnComponentType = {
  data: KanbanColumnData;
  row: number;
};

function KanbanColumnComponent({ data, row }: KanbanColumnComponentType) {
  const { major, title, apply, comment, isHearted, heart, id } = data;
  const { generation } = useParams();

  const onClickDetail = () => {
    window.location.href = `/kanban/${generation}/detail?id=${id}&row=${row}`;
  };

  return (
    <div
      className="border-[1px] border-[#F0F0F0] w-full p-3 rounded-lg drop-shadow-md bg-white hover:border-[#7AA0FF]"
      onClick={onClickDetail}
    >
      <div className="text-xs text-[#666666]">{major}</div>
      <div className="font-bold">{title}</div>
      <div className="mt-2 flex justify-between items-center text-sm text-[#666666]">
        <div className="text-sm">{apply.join(" / ")}</div>
        <div className="flex gap-3">
          <div className="flex">
            <img src="/icons/bubble.right.svg" alt="comment" />
            {comment}
          </div>
          <div className="flex">
            {isHearted ? (
              <img src="/icons/heart.point.svg" alt="heart" />
            ) : (
              <img src="/icons/heart.svg" alt="heart" />
            )}
            {heart}
          </div>
        </div>
      </div>
    </div>
  );
}

export default KanbanColumnComponent;
