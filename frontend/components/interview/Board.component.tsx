import InterviewDetailLeftComponent from "./DetailLeft.component";
import Board from "../common/board/Board.component";
import InterviewDetailRightComponent from "./DetailRight.component";
import { getInterviewRecord } from "@/src/apis/interview";
import { getScore } from "@/src/apis/score";

const accessToken = "383c8a9e-4ef6-4a62-a2d0-d0a1527eb03b";

const InterviewBoardComponent = async () => {
  const data = await getInterviewRecord(accessToken);
  const scoreData = await getScore(accessToken);

  const boardInterviewData = Array.from({ length: 10 }).map((_, i) => ({
    id: i,
    title: "[개발자]임채승",
    subElements: ["APP", "WEB", "4.5"],
    time: new Date(),
  }));

  return (
    <Board
      baseUrl={""}
      wapperClassname="divide-x"
      boardData={boardInterviewData}
    >
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <InterviewDetailLeftComponent data={data} scoreData={scoreData} />
        </div>
      </div>
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <InterviewDetailRightComponent data={data} />
        </div>
      </div>
    </Board>
  );
};

export default InterviewBoardComponent;
