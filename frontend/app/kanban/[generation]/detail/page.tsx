import { FC } from "react";
import KanbanDetailRow from "@/components/kanban/detail/Row.component";
import KanbanDetailContent from "@/components/kanban/detail/Content.component";

interface KanbanBoardDetailPageProps {
  params: {
    generation: string;
  };
  searchParams: {
    id: string;
    row: string;
  };
}

const KanbanBoardDetailPage: FC<KanbanBoardDetailPageProps> = ({
  params,
  searchParams,
}) => {
  const { generation } = params;

  const detailId = searchParams.id ?? "0";
  const detailRow = searchParams.row ?? "0";

  return (
    <div className="flex mt-8 overflow-auto pt-12 pl-12">
      <KanbanDetailRow detailRow={detailRow} generation={generation} />
      <KanbanDetailContent detailId={detailId} generation={generation} />
    </div>
  );
};

export default KanbanBoardDetailPage;
