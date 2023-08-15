import Board from "../common/board/Board.component";
import ApplicantDetail from "./Detail.component";

const ApplicantBoard = () => {
  return (
    <Board baseUrl={""} wapperClassname="divide-x">
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12"></div>
      </div>
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <ApplicantDetail applicantId="1" />
        </div>
      </div>
    </Board>
  );
};

export default ApplicantBoard;
