import { https } from "../../functions/axios";

export interface CommentRes {
  id: string;
  content: string;
  createdAt: string;
  interviewerName: string;
  isLike: boolean;
  likeCount: number;
  canEdit: boolean;
}

export interface CommentReq {
  content: string;
  parentCommentId: number;
  applicantId: string;
  cardId: number;
}

export const getAllCommentById = async (applicantId: string) => {
  const { data } = await https.get<CommentRes[]>(
    `/applicants/${applicantId}/comments`
  );
  return data;
};

export const postComment = async (body: CommentReq) => {
  const { data } = await https.post<string>(`/comments`, body);
  return data;
};

export const putComment = async ({
  commentId,
  content,
}: {
  commentId: string;
  content: string;
}) => {
  const { data } = await https.put<string>(`/comments/${commentId}`, {
    content,
  });

  return data;
};

export const deleteComment = async (commentId: string) => {
  const { data } = await https.delete<number>(`/comments/${commentId}`);
  return data;
};

export const getCardsByCardId = async (cardId: string) => {
  const { data } = await https.get<CommentRes[]>(`/cards/${cardId}/comments`);
  return data;
};

export const getCardsByApplicantId = async (applicantId: string) => {
  const { data } = await https.get<CommentRes[]>(
    `/applicants/${applicantId}/comments`
  );
  return data;
};
