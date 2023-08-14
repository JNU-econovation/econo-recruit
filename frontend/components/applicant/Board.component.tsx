import Board from "../common/board/Board.component";
import ApplicantDetail from "./Detail.component";

const ApplicantBoard = () => {
  return (
    <Board baseUrl={"asfd"}>
      <ApplicantDetail applicantId="1" />
    </Board>
  );
};

export default ApplicantBoard;
