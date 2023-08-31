import { https } from "@/src/functions/axios";

export interface InterviewRes {
  url: string;
  record: string;
}

export const getInterviewRecord = async (id: string) => {
  const { data } = await https.get<InterviewRes>(`/records`, {
    params: { applicantId: id },
  });
  return data;
};

export interface interviewReqBody {
  applicantId: string;
  url: string;
  recode: string;
}

export const postInterviewRecord = async (recode: interviewReqBody) => {
  const { data } = await https.post<interviewReqBody>(`/records`, recode);

  return data;
};

export const getInterviewRecordAll = async () => {
  const { data } = await https.get<InterviewRes[]>(`/records/all`);

  return data;
};

interface InterviewerReq {
  id: number;
  name: string;
  year: number;
  role: string;
}

export const getAllInterviewer = async () => {
  const { data } = await https.get<InterviewerReq[]>(`/interviewers/all`);

  return data;
};
