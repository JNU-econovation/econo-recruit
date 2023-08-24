import { InterviewScoreMock } from "@/mock/MockData";
import { getScoreAverage } from "@/src/functions/calculator";

const InterviewLabelComponent = () => {
  const interviewScoreList = InterviewScoreMock;

  // 서버에서 보내주는 데이터 형식에 따라 수정해야함
  const scoreAverage = getScoreAverage(
    interviewScoreList.map((row) => Number(row.score))
  );

  return (
    <div className="flex w-full items-center mt-10 gap-[6%]">
      <div className="w-[6.7rem]">
        <span className="text-5xl font-extrabold text-[#333333] relative">
          <div className="absolute w-[6.7rem] h-5 bg-[#A6BFFF] top-9 -z-10"></div>
          {scoreAverage}
        </span>
      </div>
      <div className="flex w-full gap-[3%]">
        {interviewScoreList.map((score) => (
          <div
            key={score.type}
            className="flex flex-col items-center justify-center gap-1 min-w-[4.1rem]"
          >
            <span className="text-2xl font-semibold text-[#4E4E4E] max-w-full">
              {score.score}
            </span>
            <span className="text-sm text-[#4F4F4F]">{score.string}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default InterviewLabelComponent;
