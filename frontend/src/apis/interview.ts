import { https } from "@/src/functions/axios";

// recode get response data
export interface InterviewRes {
  url: string;
  recode: string;
}

export const getInterviewRecode = async (id: string) => {
  const { data } = await https.get<InterviewRes>(`/recodes`, {
    params: { applicantId: id },
  });

  return data;
};

// recode post request body
export interface interviewReqBody {
  applicantId: string;
  url: string;
  recode: string;
}

export const postInterviewRecode = async (recode: interviewReqBody) => {
  const { data } = await https.post<interviewReqBody>(`/recodes`, recode);

  return data;
};
