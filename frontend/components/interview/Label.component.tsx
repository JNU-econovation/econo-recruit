import { InterviewScoreMock } from "@/mock/MockData";
import { getScoreAverage } from "@/src/utils/calculator";

const InterviewLabelComponent = () => {
  const interviewScoreList = InterviewScoreMock;

  // 서버에서 보내주는 데이터 형식에 따라 수정해야함
  const scoreAverage = getScoreAverage(
    interviewScoreList.map((row) => Number(row.score))
  );

  return (
    <div className="flex w-full justify-between items-center mt-8">
      <div className="relative w-[6.7rem] h-[3.5rem]">
        <div className="absolute w-[6.7rem] h-5 bg-[#A6BFFF] top-8"></div>
        <span className="absolute text-5xl font-extrabold z-30 text-[#333333]">
          {scoreAverage}
        </span>
      </div>
      <div className="flex gap-8">
        {interviewScoreList.map((score) => (
          <div key={score.type} className="flex flex-col items-center gap-1">
            <span className="text-[1.6875rem] font-semibold text-[#4E4E4E]">
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
