import { https } from "@/src/functions/axios";
import {
  KanbanCardData,
  KanbanColumnData,
} from "../stores/kanban/Kanban.atoms";

export interface KanbanCardReq {
  id: string;
  boardId: number;
  nextBoardId: number;
  cardId: number;
  cardType: "WORK_CARD" | "APPLICANT";
  title: string;
  content: string;
  labelCount: number;
  commentCount: number;
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
      params: { title: title },
    }
  );

  return data;
};

export const getAllKanbanData = async (
  navigationId: string
): Promise<KanbanColumnData[]> => {
  const columnsData = await getColums(navigationId);
  const cardsData = await Promise.all(
    columnsData.map((column) => getKanbanCards(column.columnsId.toString()))
  );
  console.log(cardsData);

  return columnsData.map((column, index) => ({
    id: column.columnsId,
    title: column.title,
    card: cardsData[index].map((card) => ({
      id: card.cardId,
      title: card.title,
      apply: [] as string[],
      comment: card.commentCount,
      heart: card.labelCount,
      isHearted: false,
    })),
  }));
};
