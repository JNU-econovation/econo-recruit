import { APPLICANT_KEYS } from "@/src/constants";
import { https } from "@/src/functions/axios";

export interface ApplicantReq {
  name: string;
  answer: string;
}

interface AllApplicantReq {
  [string: string]: string;
}

export const getApplicant = async (id: string, fields?: string[]) => {
  if (fields === undefined) {
    fields = APPLICANT_KEYS;
  }
  const { data } = await https.get<AllApplicantReq>(`/applicants/${id}`, {
    params: { fields },
  });
  return Object.keys(data).map((key) => ({
    name: key,
    answer: data[key],
  }));
};

export const getAllApplicant = async (): Promise<ApplicantReq[][]> => {
  const { data } = await https.get<AllApplicantReq[]>(`/applicants`);
  return data.map((d) =>
    Object.keys(d).map((key) => ({
      name: key,
      answer: d[key],
    }))
  );
};

export interface ApplicantLabelReq {
  name: string;
  active: boolean;
}

export const getApplicantLabel = async (id: string) => {
  const { data } = await https.get<ApplicantLabelReq[]>(
    `/labels?applicantId=${id}`
  );

  return data;
};

export const getApplicantTimeTables = async (id: string) => {
  const { data } = await https.get<number[]>(`/applicants/${id}/timetables`);

  return data;
};
