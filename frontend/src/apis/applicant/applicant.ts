import { APPLICANT_KEYS } from "@/src/constants";
import { https } from "@/src/functions/axios";
import {
  getAllInterviewer,
  getInterviewRecordAll,
} from "../interview/interview";

export interface ApplicantReq {
  name: string;
  answer: string;
}

interface AllApplicantReq {
  [string: string]: string;
}

export const getApplicantByIdWithField = async (
  id: string,
  fields?: string[]
) => {
  if (fields === undefined) {
    fields = APPLICANT_KEYS;
  }
  const { data } = await https.post<AllApplicantReq>(`/boards/${id}`, fields);

  return Object.keys(data).map((key) => ({
    name: key,
    answer: data[key],
  }));
};

export const getAllApplicant = async (
  fields?: string[]
): Promise<ApplicantReq[][]> => {
  if (fields === undefined) {
    fields = APPLICANT_KEYS;
  }
  const { data } = await https.post<AllApplicantReq[]>(`/boards`, fields);
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
  const allInterviewers = await getAllInterviewer();

  try {
    const { data } = await https.get<string[]>(`/applicants/${id}/labels`);
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

export const postApplicantLabel = async (
  id: string
): Promise<ApplicantLabelReq[]> => {
  const { data } = await https.post<ApplicantLabelReq[]>(
    `/applicants/${id}/labels`
  );

  return data;
};

export const getApplicantTimeTables = async (id: string) => {
  const { data } = await https.get<number[]>(`/applicants/${id}/timetables`);

  return data;
};