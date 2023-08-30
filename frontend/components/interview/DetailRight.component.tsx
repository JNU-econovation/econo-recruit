import { InterviewRes } from "@/src/apis/interview";
import { FC } from "react";

interface InterviewDetailRightProps {
  data: InterviewRes;
}

const InterviewDetailRightComponent: FC<InterviewDetailRightProps> = async ({
  data,
}) => {
  console.log(data.record);
  return (
    <>
      <pre className="whitespace-pre-wrap">{data.record}</pre>
    </>
  );
};

export default InterviewDetailRightComponent;
