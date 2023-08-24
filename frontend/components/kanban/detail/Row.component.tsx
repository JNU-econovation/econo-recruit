"use client";

import { KanbanDataArrayState } from "@/src/stores/kanban/Kanban.atoms";
import { useAtomValue } from "jotai";
import { FC } from "react";
import KanbanColumnComponent from "../Column.component";
import KanbanDetailBackButton from "./BackButton.component";

interface KanbanDetailRowProps {
  detailRow: string;
  generation: string;
}

const KanbanDetailRow: FC<KanbanDetailRowProps> = ({
  detailRow,
  generation,
}) => {
  const kanbanDataArray = useAtomValue(KanbanDataArrayState);
  const rowTitle = kanbanDataArray[+detailRow].title;
  const columnCount = kanbanDataArray[+detailRow].column.length;

  return (
    <div className="max-h-[calc(100vh-20rem)]">
      <div className="p-4">
        <KanbanDetailBackButton generation={generation} />
      </div>
      <div className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white">
        <div className="flex justify-between">
          <div className="flex gap-2 items-center">
            <div className="font-bold text-lg">{rowTitle}</div>
            <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
              {columnCount}
            </div>
          </div>
          <button>
            <img src="/icons/ellipsis.bubble.svg" alt="RowDetail" />
          </button>
        </div>
        <div className="flex flex-col justify-between">
          {kanbanDataArray[+detailRow].column.map((col) => (
            <div className="my-2" key={col.id}>
              <KanbanColumnComponent data={col} row={+detailRow} />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default KanbanDetailRow;
