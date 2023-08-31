"use client";

import InterviewDetailLeftComponent from "./modal/DetailLeft.component";
import Board from "../common/board/Board.component";
import InterviewDetailRightComponent from "./modal/DetailRight.component";
import {
  InterviewRes,
  getInterviewRecord,
} from "@/src/apis/interview/interview";
import { ScoreRes, getScore } from "@/src/apis/interview/score";
import { useState } from "react";
import { applicantDataFinder } from "@/src/functions/finder";
import { getAllApplicant } from "@/src/apis/applicant/applicant";
import { useQuery } from "@tanstack/react-query";

const InterviewBoardComponent = () => {
  const [applicantId, setApplicantId] = useState<string>("");
  const initData = {
    record: "",
    url: "",
  } as InterviewRes;

  const initScoreData = {
    totalAverage: 0,
    scoreVo: {
      average: [],
    },
  } as ScoreRes;

  const [data, setData] = useState<InterviewRes>(initData);
  const [scoreData, setScoreData] = useState<ScoreRes>(initScoreData);

  const onClick = (id: string) => {
    setApplicantId(id);
    getInterviewRecord(id)
      .then((res) => {
        setData(res);
      })
      .catch((err) => {
        console.log(err);
        setData(initData);
      });

    getScore(id)
      .then((res) => {
        setScoreData(res);
      })
      .catch((err) => {
        console.log(err);
        setScoreData(initScoreData);
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
          <InterviewDetailLeftComponent
            data={data}
            scoreData={scoreData}
            applicantId={applicantId}
          />
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
