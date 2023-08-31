import { KanbanCardReq } from "../../apis/kanban/kanban";
import { atom } from "jotai";

export type KanbanColumnData = {
  id: number;
  title: string;
  card: (KanbanCardData | null)[];
};

export type KanbanCardData = {
  id: number;
  title: string;
  apply: string[];
  comment: number;
  heart: number;
  isHearted: boolean;
};

export const KanbanDataArrayState = atom({} as KanbanColumnData[]);
