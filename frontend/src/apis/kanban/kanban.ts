import { https } from "@/src/functions/axios";
import {
  KanbanCardData,
  KanbanColumnData,
} from "../../stores/kanban/Kanban.atoms";

export interface KanbanCardReq {
  id: number;
  boardId: number;
  columnId: number;
  nextBoardId: number;
  cardType: "WORK_CARD" | "APPLICANT" | "INTERVIEW";
  title: string;
  content: string;
  labelCount: number;
  major: string;
  applicantId: string;
  commentCount: number;
  firstPriority: string;
  secondPriority: string;
  isLabeled: boolean;
}

// card api 추가 시 수정 필요
export const getKanbanCards = async (columnId: string) => {
  const { data } = await https.get<KanbanCardReq[]>(
    `/navigations/${columnId}/boards`
  );

  return data;
};

interface KanbanNavigationReq {
  columnsId: number;
  title: string;
  navigationId: number;
}

export const getColums = async (navigationId: string) => {
  const { data } = await https.get<KanbanNavigationReq[]>(
    `/boards/navigations/${navigationId}/columns`
  );

  return data;
};

// export interface
interface addColumnReq {
  navigationId: string;
  title: string;
}

export const postAddColumn = async ({ navigationId, title }: addColumnReq) => {
  const { data } = await https.post<string>(
    `/boards/navigations/${navigationId}/columns`,
    null,
    {
      params: { title },
    }
  );

  return data;
};

export const getAllKanbanData = async (
  navigationId: string
): Promise<KanbanColumnData[]> => {
  const columnsData = await getColums(navigationId);
  const cardsData = await getKanbanCards(navigationId);

  return columnsData.map((column) => ({
    id: column.columnsId,
    title: column.title,
    card: cardsData
      .filter((card) => card.columnId === column.columnsId)
      .map((card) => ({
        id: card.id,
        title: card.title,
        major: card.major.split('"').join(""),
        applicantId: card.applicantId,
        apply: [
          card.firstPriority.split('"').join(""),
          card.secondPriority.split('"').join(""),
        ],
        comment: card.commentCount,
        heart: card.labelCount,
        isHearted: card.isLabeled,
      })),
  }));
};
