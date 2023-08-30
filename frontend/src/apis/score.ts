import { https } from "../functions/axios";

export interface scoreDetail {
  creteria: "열정" | "실천력" | "협업" | "동아리 집중" | "간절함";
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
