import { InterviewScoreMock } from "@/mock/MockData";
import { ScoreRes, scoreDetail } from "@/src/apis/score";
import { getScoreAverage } from "@/src/functions/calculator";
import { FC } from "react";

interface InterviewAvgScoreProps {
  totalAverage: number;
  average: scoreDetail[];
}

const InterviewAvgScoreComponent: FC<InterviewAvgScoreProps> = ({
  totalAverage,
  average,
}) => {
  return (
    <div className="flex w-full items-center mt-10 gap-[6%]">
      <div className="w-[6.7rem]">
        <span className="text-5xl font-extrabold text-[#333333] relative">
          <div className="absolute w-[6.7rem] h-5 bg-[#A6BFFF] top-9 -z-10"></div>
          {totalAverage}
        </span>
      </div>
      <div className="flex w-full gap-[3%]">
        {average.map((score) => (
          <div
            key={score.creteria}
            className="flex flex-col items-center justify-center gap-1 min-w-[4.1rem]"
          >
            <span className="text-2xl font-semibold text-[#4E4E4E] max-w-full">
              {score.score}
            </span>
            <span className="text-sm text-[#4F4F4F]">{score.creteria}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default InterviewAvgScoreComponent;
