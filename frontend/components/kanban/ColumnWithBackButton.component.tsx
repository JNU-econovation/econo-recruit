"use client";

import {
  KanbanColumnData,
  KanbanDataArrayState,
} from "@/src/stores/kanban/Kanban.atoms";
import { useAtom, useAtomValue } from "jotai";
import { FC } from "react";
import KanbanCardComponent from "./Card.component";
import KanbanDetailBackButton from "./detail/BackButton.component";
import { useQuery } from "@tanstack/react-query";
import { getAllKanbanData } from "@/src/apis/kanban/kanban";
import { KanbanSelectedButtonNumberState } from "@/src/stores/kanban/Navbar.atoms";

interface KanbanDetailCardProps {
  detailCard: string;
  generation: string;
}

const KanbanColumnDetailCard: FC<KanbanDetailCardProps> = ({
  detailCard,
  generation,
}) => {
  const [navbarId] = useAtom(KanbanSelectedButtonNumberState);
  const {
    data: kanbanDataArray,
    isError,
    isLoading,
  } = useQuery<KanbanColumnData[]>(["kanbanDataArray", generation], () =>
    getAllKanbanData(navbarId)
  );

  if (!kanbanDataArray || isLoading) {
    return <div>로딩중...</div>;
  }

  if (isError) {
    return <div>에러 발생</div>;
  }

  const cardTitle = kanbanDataArray[+detailCard].title;
  const cardCount = kanbanDataArray[+detailCard].card.length;

  return (
    <div className="max-h-[calc(100vh-20rem)]">
      <div className="p-4">
        <KanbanDetailBackButton generation={generation} />
      </div>
      <div className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white">
        <div className="flex justify-between">
          <div className="flex gap-2 items-center">
            <div className="font-bold text-lg">{cardTitle}</div>
            <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
              {cardCount}
            </div>
          </div>
          <button>
            <img src="/icons/ellipsis.bubble.svg" alt="CardDetail" />
          </button>
        </div>
        <div className="flex flex-col justify-between">
          {kanbanDataArray[+detailCard].card.map(
            (col) =>
              col && (
                <div className="my-2" key={col.id}>
                  <KanbanCardComponent data={col} cardId={+detailCard} />
                </div>
              )
          )}
        </div>
      </div>
    </div>
  );
};

export default KanbanColumnDetailCard;
