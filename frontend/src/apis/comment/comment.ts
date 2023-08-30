import { https } from "../../functions/axios";

export interface CommentRes {
  createdAt: string;
  interviewerName: string;
  content: string;
  isLike: boolean;
  likeCount: number;
}

export interface CommentReq {
  content: string;
  parentId: number; // 대댓글 시 부모 댓글의 id
  applicantId: string;
  idpId: string;
}

export const getComment = async (cardId: string) => {
  const { data } = await https.get<CommentRes[]>(`/comments/`, {
    params: { cardId: cardId },
  });
  return data;
};

export const postComment = async (body: CommentReq) => {
  const { data } = await https.post<string>(`/comments/`, body);
  return data;
};

export const putComment = async (commentId: string, content: string) => {
  const { data } = await https.put<string>(`/comments/`, {
    params: { commentId: commentId, content: content },
  });
  return data;
};

export const deleteComment = async (commentId: string) => {
  const { data } = await https.delete<number>(`/comments`, {
    params: { commentId: commentId },
  });
  return data;
};
