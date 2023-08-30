import { InterviewRes } from "@/src/apis/interview";
import { FC } from "react";

interface InterviewDetailRightProps {
  data: InterviewRes;
}

const InterviewDetailRightComponent: FC<InterviewDetailRightProps> = async ({
  data,
}) => {
  return (
    <>
      <pre className="whitespace-pre-wrap">{data.recode}</pre>
    </>
  );
};

export default InterviewDetailRightComponent;
