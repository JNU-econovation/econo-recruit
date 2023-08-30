import { https } from "../functions/axios";

export interface scoreDetail {
  creteria: string;
  score: number;
}

export interface ScoreVo {
  average: scoreDetail[];
  [key: string]: scoreDetail[];
}

// score get response data
export interface ScoreRes {
  totalAverage: number;
  scoreVo: ScoreVo;
}

export const getScore = async (id: string) => {
  const { data } = await https.get<ScoreRes>(`/scores`, {
    params: { applicantId: id },
  });

  return data;
};
