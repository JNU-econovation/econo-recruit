import InterviewUserComponent from "./User.component";
import { InterviewRes } from "@/src/apis/interview/interview";
import { ScoreRes } from "@/src/apis/interview/score";
import { FC } from "react";
import InterviewAvgComponent from "@/components/interview/modal/AvgScore.component";
import InterviewScoreComponent from "./Score.component";
import InterviewUploadComponent from "./Upload.component";
import InterviewEditComponent from "./Edit.component";

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
      {!data ? (
        <InterviewEditComponent applicantId={applicantId} />
      ) : (
        <InterviewUploadComponent applicantId={applicantId} />
      )}
    </>
  );
};

export default InterviewDetailLeftComponent;
