import { https } from "@/src/functions/axios";

export interface ApplicantCommentReq {
  interviewerName: string;
  content: string;
  isLike: boolean;
  likeCount: number;
  createAt: number;
  canEdit: boolean;
}

export const getApplicantComment = async (id: string) => {
  const { data } = await https.get<ApplicantCommentReq[]>(`/comments/${id}`);

  return data;
};

export const putApplicantComment = async (id: string, body: string) => {
  const { data } = await https.put(`/comments/${id}`, { content: body });

  return data;
};

export const postApplicantComment = async (id: string, body: string) => {
  const { data } = await https.post(`/comments/${id}`, { content: body });

  return data;
};

export const deleteApplicantComment = async (id: string) => {
  const { data } = await https.delete(`/comments/${id}`);

  return data;
};

export const postApplicantCommentLike = async (id: string) => {
  const { data } = await https.post(`/comments/likes`, { commentId: id });

  return data;
};

export const deleteApplicantCommentLike = async (id: string) => {
  const { data } = await https.delete(`/comments/likes`, {
    data: { commentId: id },
  });

  return data;
};
