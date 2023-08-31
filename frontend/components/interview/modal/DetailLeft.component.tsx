import InterviewUserComponent from "./User.component";
import { InterviewRes } from "@/src/apis/interview/record";
import { ScoreRes } from "@/src/apis/interview/score";
import { FC } from "react";
import InterviewAvgComponent from "@/components/interview/modal/AvgScore.component";
import InterviewScoreComponent from "./Score.component";
import InterviewEditRecordComponent from "./EditRecord.component";

interface InterviewDetailLeftProps {
  applicantId: string;
  data: InterviewRes;
  scoreData: ScoreRes;
}

const InterviewDetailLeftComponent: FC<InterviewDetailLeftProps> = ({
  applicantId,
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
      <InterviewEditRecordComponent applicantId={applicantId} data={data} />
    </>
  );
};

export default InterviewDetailLeftComponent;
