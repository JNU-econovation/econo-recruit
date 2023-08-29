import { https } from "@/src/functions/axios";

export interface ApplicantReq {
  name: string;
  answer: string;
}

export const getApplicant = async (id: string) => {
  const { data } = await https.get<ApplicantReq[]>(`/applicants/${id}`);

  return data;
};

export interface AllApplicantReq {
  [string: string]: ApplicantReq[];
}

export const getAllApplicant = async () => {
  const { data } = await https.get<AllApplicantReq[]>(`/applicants`);

  return data;
};

export interface ApplicantLabelReq {
  name: string;
  active: boolean;
}

export const getApplicantLabel = async (id: string) => {
  const { data } = await https.get<ApplicantLabelReq[]>(
    `/applicants/${id}/label`
  );

  return data;
};
