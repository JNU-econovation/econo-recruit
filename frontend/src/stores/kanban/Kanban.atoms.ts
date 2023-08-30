import { atom } from "jotai";
import { KanbanMock } from "@/mock/MockData";

export type KanbanRowData = {
  id: number;
  title: string;
  Card: KanbanCardData[];
};

export type KanbanCardData = {
  id: number;
  major: string;
  title: string;
  apply: string[];
  comment: number;
  heart: number;
  isHearted: boolean;
};

export const KanbanDataArrayState = atom(KanbanMock as KanbanRowData[]);
