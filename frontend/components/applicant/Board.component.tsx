"use client";

import Board from "@/components/common/board/Board.component";
import {
  AllApplicantReq,
  getAllApplicant,
  getApplicant,
} from "@/src/apis/applicant/applicant";
import ApplicantDetailRight from "./DetailRight.component";
import ApplicantDetailLeft from "./DetailLeft.component";
import { FC, useEffect, useState } from "react";
import { ApplicantReq } from "@/src/apis/application";
import { applicantDataFinder } from "@/src/functions/finder";

interface ApplicantBoardProps {
  generation: string;
}

const ApplicantBoard: FC<ApplicantBoardProps> = ({ generation }) => {
  const [allData, setAllData] = useState<AllApplicantReq[]>([]);
  const [data, setData] = useState<ApplicantReq[]>([]);

  const onClick = (id: string) => {
    getApplicant(id).then((res) => {
      setData(res);
    });
  };

  useEffect(() => {
    getAllApplicant().then((res) => {
      setAllData(res);
    });
  }, []);

  return (
    <Board
      wapperClassname="divide-x"
      boardData={allData.map((value) => ({
        id: Object.keys(value)[0],
        title: applicantDataFinder(value[Object.keys(value)[0]], "name"),
        subElements: [
          applicantDataFinder(value[Object.keys(value)[0]], "field"),
          applicantDataFinder(value[Object.keys(value)[0]], "major"),
        ],
        time: new Date(
          applicantDataFinder(value[Object.keys(value)[0]], "time")
        ),
      }))}
      onClick={onClick}
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
