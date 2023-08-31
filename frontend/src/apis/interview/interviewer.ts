import { https } from "@/src/functions/axios";

interface InterviewerReq {
  id: number;
  name: string;
  year: number;
  role: string;
}

export const getAllInterviewer = async () => {
  const { data } = await https.get<InterviewerReq[]>(`/interviewers`);

  return data;
};
