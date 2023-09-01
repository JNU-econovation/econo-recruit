import InterviewUserComponent from "./User.component";
import { InterviewRes } from "@/src/apis/interview/record";
import { ScoreRes } from "@/src/apis/interview/score";
import { FC } from "react";
import InterviewAvgComponent from "@/components/interview/modal/AvgScore.component";
import InterviewScoreComponent from "./Score.component";
import InterviewEditComponent from "./Edit.component";
import InterviewUploadComponent from "./Upload.component";

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
  const isDataExist = !!data.url || !!data.record;
  return (
    <>
      <InterviewUserComponent src={data.url} />
      <InterviewAvgComponent
        totalAverage={scoreData.totalAverage}
        average={scoreData.scoreVo.average}
      />
      <InterviewScoreComponent score={scoreData} />
      {isDataExist ? (
        <InterviewEditComponent applicantId={applicantId} data={data} />
      ) : (
        <InterviewUploadComponent applicantId={applicantId} />
      )}
    </>
  );
};

export default InterviewDetailLeftComponent;
