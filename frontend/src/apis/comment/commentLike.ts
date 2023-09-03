import { https } from "@/src/functions/axios";

export const getCommentsIsLike = async (commentId: string) => {
  const { data } = await https.get<boolean>(`/comments/${commentId}/is-like`);

  return data;
};

export const postCommentsLike = async (commentId: string) => {
  const { data } = await https.post<string>(`/comments/${commentId}/likes`, {
    params: { commentId: commentId },
  });

  return data;
};

export const deleteCommentsLike = async (commentId: string) => {
  const { data } = await https.delete<string>(`/comments/likes`, {
    params: { commentId: commentId },
  });

  return data;
};
