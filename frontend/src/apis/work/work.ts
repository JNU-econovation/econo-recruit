import { https } from "@/src/functions/axios";

export interface Work {
  title: string;
  content: string;
}

export const getWork = async (cardId: string) => {
  const response = await https.get<Work>(`/cards/${cardId}`);
  return response.data;
};

export const putWork = async ({
  cardId,
  title,
  content,
}: {
  cardId: number;
  title?: string;
  content?: string;
}) => {
  const data =
    title === "" || !title
      ? { content }
      : content === "" || !content
      ? {}
      : { title, content };
  const response = await https.put<Work>(`/boards/cards/${cardId}`, data);
  return response.data;
};
