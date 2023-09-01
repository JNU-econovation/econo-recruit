"use client";

import InterviewDetailLeftComponent from "./modal/DetailLeft.component";
import Board from "../common/board/Board.component";
import InterviewDetailRightComponent from "./modal/DetailRight.component";
import { applicantDataFinder } from "@/src/functions/finder";
import { getAllApplicant } from "@/src/apis/applicant/applicant";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { useAtom } from "jotai";
import { interViewApplicantIdState } from "@/src/stores/interview/Interview.atom";

const InterviewBoardComponent = () => {
  const [applicantId, setApplicantId] = useAtom(interViewApplicantIdState);

  const queryClient = useQueryClient();

  const onClick = (id: string) => {
    setApplicantId(id);
    queryClient.invalidateQueries({
      queryKey: ["record", applicantId],
    });
    queryClient.invalidateQueries({
      queryKey: ["score", applicantId],
    });
  };

  const { data: allData, isLoading } = useQuery({
    queryKey: ["allApplicant"],
    queryFn: () => getAllApplicant(),
  });

  if (!allData || isLoading) {
    return <div>loading...</div>;
  }

  const boardData = allData.map((value) => ({
    id: applicantDataFinder(value, "id"),
    title: `[${applicantDataFinder(value, "field")}] ${applicantDataFinder(
      value,
      "name"
    )}`,
    subElements: [
      applicantDataFinder(value, "field1"),
      applicantDataFinder(value, "field2"),
      applicantDataFinder(value, "major"),
    ],
  }));

  return (
    <Board
      wapperClassname="divide-x"
      boardData={boardData}
      onClick={(id) => onClick(id)}
    >
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <InterviewDetailLeftComponent />
        </div>
      </div>
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <InterviewDetailRightComponent />
        </div>
      </div>
    </Board>
  );
};

export default InterviewBoardComponent;
