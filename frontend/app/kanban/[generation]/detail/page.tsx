import { FC } from "react";
import KanbanDetailColumn from "@/components/kanban/ColumnWithBackButton.component";
import KanbanDetailContent from "@/components/kanban/detail/Content.component";

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
      <KanbanDetailColumn detailCard={detailCard} generation={generation} />
      <KanbanDetailContent detailId={detailId} generation={generation} />
    </div>
  );
};

export default KanbanBoardDetailPage;
