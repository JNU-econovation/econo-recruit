import { https } from "@/src/functions/axios";
import { KanbanColumnData } from "../../stores/kanban/Kanban.atoms";

export interface KanbanCardReq {
  id: number;
  boardId: number;
  columnId: number;
  nextBoardId: number | null;
  cardType: "WORK_CARD" | "APPLICANT" | "INVISIBLE";
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
  nextColumnsId: number;
}

export const getColums = async (navigationId: string) => {
  const { data } = await https.get<KanbanNavigationReq[]>(
    `/boards/navigations/${navigationId}/columns`
  );

  const startColumn = data.filter((column) => column.nextColumnsId === null);

  const locationSort = (
    column: KanbanNavigationReq[]
  ): KanbanNavigationReq[] => {
    if (column.length === data.length) return column;

    const nextColumnId = column[column.length - 1].columnsId;
    const nextColumn = data.filter(
      (column) => column.nextColumnsId === nextColumnId
    );

    return locationSort([...column, ...nextColumn]);
  };

  return locationSort(startColumn).reverse();
};

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

interface addCardReq {
  columnId: number;
  title: string;
}

export const postAddCard = async ({ columnId, title }: addCardReq) => {
  const { data } = await https.post<string>(`/boards/work-cards`, {
    columnId,
    title,
    content: "",
  });

  return data;
};

export const getAllKanbanData = async (
  navigationId: string
): Promise<KanbanColumnData[]> => {
  const columnsData = await getColums(navigationId);
  const cardsData = await getKanbanCards(navigationId);

  return columnsData.map((column) => {
    const columnCardData = cardsData
      .filter((card) => card.columnId === column.columnsId)
      .filter((card) => card.cardType === "INVISIBLE");

    const findLocationData = (
      columnCardsData: KanbanCardReq[]
    ): KanbanCardReq[] => {
      if (columnCardsData[columnCardsData.length - 1].nextBoardId === null) {
        return columnCardsData;
      }
      const nextBoardId =
        columnCardsData[columnCardsData.length - 1].nextBoardId;

      const nextColumnCardsData = cardsData.filter(
        (card) => card.boardId === nextBoardId
      );

      return findLocationData([...columnCardsData, ...nextColumnCardsData]);
    };

    return {
      id: column.columnsId,
      title: column.title,
      card: findLocationData(columnCardData)
        .map((card) => {
          if (!card) return null;
          return {
            id: card.boardId,
            cardType: card.cardType,
            title: card.title,
            major: card.major.split('"').join(""),
            applicantId: card.applicantId,
            apply: [
              card.firstPriority.split('"').join(""),
              card.secondPriority.split('"').join(""),
            ].filter((apply) => apply !== ""),
            comment: card.commentCount,
            heart: card.labelCount,
            isHearted: card.isLabeled,
          };
        })
        .filter((card) => card !== null),
    };
  });
};
