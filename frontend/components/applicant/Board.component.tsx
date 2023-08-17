import Board from "@/components/common/board/Board.component";
import { getApplicant } from "@/src/apis/applicant";
import ApplicantDetailRight from "./DetailRight.component";
import ApplicantDetailLeft from "./DetailLeft.component";

const getApplicantDetailData = async (applicantId: string) => {
  return await getApplicant(applicantId);
};

const ApplicantBoard = async () => {
  const data = await getApplicantDetailData("22");

  return (
    <Board baseUrl={""} wapperClassname="divide-x">
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <ApplicantDetailLeft data={data} />
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
