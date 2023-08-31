import { https } from "@/src/functions/axios";

export interface KanbanNavReq {
  id: string;
  navTitle: string;
}

export const getAllKanbanNav = async () => {
  const { data } = await https.get<KanbanNavReq[]>("/boards/navigations");

  return data;
};

export const getKanbanNav = async (navId: string) => {
  const { data } = await https.get<KanbanNavReq>(
    `/boards/navigations/${navId}`
  );

  return data;
};

export const postKanbanNav = async (navTitle: string) => {
  const { data } = await https.post<KanbanNavReq>("/boards/navigations", {
    params: { navTitle },
  });

  return data;
};
