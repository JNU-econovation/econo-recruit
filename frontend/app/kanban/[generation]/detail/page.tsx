"use client";

import { FC } from "react";
import KanbanColumnDetailCard from "@/components/kanban/column/ColumnWithBackButton.component";
import KanbanDetailContent from "@/components/kanban/detail/ContentApplicant.component";
import Validate from "@/components/user/Validate.component";

interface KanbanBoardDetailPageProps {
  params: {
    generation: string;
  };
  searchParams: {
    id: string;
    card: string;
  };
}

const KanbanBoardDetailPage: FC<KanbanBoardDetailPageProps> = ({
  params,
  searchParams,
}) => {
  const { generation } = params;

  const detailId = searchParams.id ?? "0";
  const detailCard = searchParams.card ?? "0";

  return (
    <div className="flex mt-8 overflow-auto pt-12 pl-12">
      <Validate />
      <KanbanColumnDetailCard detailCard={detailCard} generation={generation} />
      <KanbanDetailContent detailId={detailId} generation={generation} />
    </div>
  );
};

export default KanbanBoardDetailPage;
