import { scoreDetail } from "@/src/apis/score";

type InterviewScoreCellComponent = {
  item: [string, scoreDetail[]];
};

const InterviewScoreCellComponent = ({ item }: InterviewScoreCellComponent) => {
  return (
    <div className="flex items-center gap-8">
      <div className="flex min-w-[6.7rem] justify-center items-center">
        <div className="text-[#2160FF] bg-[#E8EFFF] px-5 py-2 rounded-3xl max-w-fit">
          {item[0]}
        </div>
      </div>

      <div className="flex w-full text-lg gap-4">
        {item[1].map((score) => (
          <span className="flex min-w-[4.1rem] justify-center items-center">
            {Math.floor(score.score)}
          </span>
        ))}
      </div>
    </div>
  );
};

export default InterviewScoreCellComponent;
