import InterviewUserComponent from "./User.component";
import { InterviewRes } from "@/src/apis/interview";
import { ScoreRes } from "@/src/apis/score";
import { FC } from "react";
import InterviewAvgComponent from "@/components/interview/AvgScore.component";
import InterviewScoreComponent from "./Score.component";

interface InterviewDetailLeftProps {
  data: InterviewRes;
  scoreData: ScoreRes;
}

const InterviewDetailLeftComponent: FC<InterviewDetailLeftProps> = async ({
  data,
  scoreData,
}) => {
  return (
    <>
      <InterviewUserComponent src={data.url} />
      <InterviewAvgComponent
        totalAverage={scoreData.totalAverage}
        average={scoreData.scoreVo.average}
      />
      <InterviewScoreComponent score={scoreData} />
    </>
  );
};

export default InterviewDetailLeftComponent;
