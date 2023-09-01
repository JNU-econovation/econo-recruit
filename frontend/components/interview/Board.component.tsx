"use client";

import InterviewDetailLeftComponent from "./modal/DetailLeft.component";
import Board from "../common/board/Board.component";
import InterviewDetailRightComponent from "./modal/DetailRight.component";
import { InterviewRes, getInterviewRecord } from "@/src/apis/interview/record";
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

  const [recordData, setData] = useState<InterviewRes>(initData);
  const [scoreData, setScoreData] = useState<ScoreRes>(initScoreData);
  const { data: fetchedRecord, refetch: refetchRecord } =
    useQuery<InterviewRes>({
      queryKey: ["record", applicantId],
      queryFn: () => getInterviewRecord(applicantId),
      onError: (err) => {
        console.log(err);
        return initData;
      },
      onSuccess: (data) => {
        setData(data);
      },
      enabled: !!applicantId,
    });
  const { data: fetchedScore, refetch: refetchScore } = useQuery<ScoreRes>({
    queryKey: ["score", applicantId],
    queryFn: () => getScore(applicantId),
    onError: (err) => {
      console.log(err);
      return initScoreData;
    },
    onSuccess: (data) => {
      setScoreData(data);
    },
    enabled: !!applicantId,
  });

  const onClick = (id: string) => {
    setApplicantId(id);
    refetchRecord();
    refetchScore();
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
            data={recordData}
            scoreData={scoreData}
            applicantId={applicantId}
          />
        </div>
      </div>
      <div className="flex flex-1 min-h-0">
        <div className="flex-1 overflow-auto px-12">
          <InterviewDetailRightComponent data={recordData} />
        </div>
      </div>
    </Board>
  );
};

export default InterviewBoardComponent;
