import { https } from "@/src/functions/axios";

export interface KanbanRowRes {
  id: string;
  navTitle: string;
}

export const getAllRow = async (id: string) => {
  const { data } = await https.get<KanbanRowRes[]>(`/boards/navigations`, {
    params: { applicantId: id },
  });
  return data;
};
