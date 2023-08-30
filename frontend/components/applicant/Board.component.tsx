import Board from "@/components/common/board/Board.component";
import { getApplicant } from "@/src/apis/applicant";
import ApplicantDetailRight from "./DetailRight.component";
import ApplicantDetailLeft from "./DetailLeft.component";
import { FC } from "react";

const getApplicantDetailData = async (applicantId: string) => {
  return await getApplicant(applicantId);
};

interface ApplicantBoardProps {
  generation: string;
}

const ApplicantBoard: FC<ApplicantBoardProps> = async ({ generation }) => {
  const data = await getApplicantDetailData("22");
  const boardApplicationData = Array.from({ length: 10 }).map((_, i) => ({
    id: i,
    title: "[개발자]임채승",
    subElements: ["APP", "WEB", "1학년 1학기"],
    time: new Date(),
  }));

  return (
    <Board
      baseUrl={""}
      wapperClassname="divide-x"
      boardData={boardApplicationData}
    >
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <ApplicantDetailLeft data={data} postId={"22"} />
        </div>
      </div>
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <ApplicantDetailRight data={data} />
        </div>
      </div>
    </Board>
  );
};

export default ApplicantBoard;
