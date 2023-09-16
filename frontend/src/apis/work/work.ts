import { https } from "@/src/functions/axios";
import { getAllInterviewer } from "../interview/interviewer";

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

export const deleteWork = async (cardId: string) => {
  const response = await https.post(`/cards/${cardId}/delete`);
  return response.data;
};

export interface WorkLabelReq {
  name: string;
  active: boolean;
}

export const postWorkLabel = async (cardId: number) => {
  const { data } = await https.post(`/cards/${cardId}/labels`);

  return data;
};

export const getWorkLabel = async (cardId: number) => {
  const allInterviewers = await getAllInterviewer();

  try {
    const { data } = await https.get<string[]>(`/cards/${cardId}/labels`);
    return allInterviewers.map((interviewer) => {
      const label = data.find((label) => label === interviewer.name);
      return {
        name: interviewer.name,
        active: !!label,
      };
    });
  } catch (e) {
    return allInterviewers.map((interviewer) => ({
      name: interviewer.name,
      active: false,
    }));
  }
};
