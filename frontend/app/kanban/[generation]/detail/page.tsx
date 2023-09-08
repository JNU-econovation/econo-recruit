"use client";

import { FC } from "react";
import KanbanColumnDetailCard from "@/components/kanban/column/ColumnWithBackButton.component";
import KanbanDetailContent from "@/components/kanban/content/DetailApplicant.component";
import Validate from "@/components/user/Validate.component";
import { KanbanCardReq } from "@/src/apis/kanban/kanban";

interface KanbanBoardDetailPageProps {
  params: {
    generation: string;
  };
  searchParams: {
    id: string;
    card: string;
    type: KanbanCardReq["cardType"];
  };
}

const KanbanBoardDetailPage: FC<KanbanBoardDetailPageProps> = ({
  params: { generation },
  searchParams: { card, id, type },
}) => {
  return (
    <div className="flex mt-8 overflow-auto pt-12 pl-12">
      <Validate />
      <KanbanColumnDetailCard detailCard={card ?? 0} generation={generation} />
      <KanbanDetailContent detailId={id ?? 0} generation={generation} />
    </div>
  );
};

export default KanbanBoardDetailPage;
