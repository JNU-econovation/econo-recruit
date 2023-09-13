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
    columnIndex: string;
    type: KanbanCardReq["cardType"];
  };
}

interface DetailContentProps {
  applicantId: string;
  generation: string;
  type: KanbanCardReq["cardType"];
}

const DetailContentJunction: FC<DetailContentProps> = ({
  applicantId,
  generation,
  type,
}) => {
  if (type === "APPLICANT") {
    return (
      <KanbanDetailContent
        detailId={applicantId ?? 0}
        generation={generation}
      />
    );
  }
  if (type === "WORK_CARD") {
    return <></>;
  }

  return <></>;
};

const KanbanBoardDetailPage: FC<KanbanBoardDetailPageProps> = ({
  params: { generation },
  searchParams: { columnIndex, id, type },
}) => {
  return (
    <main className="flex mt-8 overflow-auto pt-12 pl-12">
      <Validate />
      <KanbanColumnDetailCard
        columnIndex={+columnIndex ?? 0}
        generation={generation}
      />
      <DetailContentJunction
        applicantId={id}
        generation={generation}
        type={type}
      />
    </main>
  );
};

export default KanbanBoardDetailPage;
