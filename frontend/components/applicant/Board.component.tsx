"use client";

import Board from "@/components/common/board/Board.component";
import { getAllApplicant, getApplicant } from "@/src/apis/applicant/applicant";
import ApplicantDetailRight from "./DetailRight.component";
import ApplicantDetailLeft from "./DetailLeft.component";
import { FC, useEffect, useState } from "react";
import { ApplicantReq } from "@/src/apis/application";
import { applicantDataFinder } from "@/src/functions/finder";

interface ApplicantBoardProps {
  generation: string;
}

const ApplicantBoard: FC<ApplicantBoardProps> = ({ generation }) => {
  const [allData, setAllData] = useState<ApplicantReq[][]>([]);
  const [data, setData] = useState<ApplicantReq[]>([]);

  const onClick = (id: string) => {
    getApplicant(id)
      .then((res) => {
        setData(res);
      })
      .catch((err) => {
        console.log(err);
        setData([]);
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
        id: applicantDataFinder(value, "id"),
        title: applicantDataFinder(value, "name"),
        subElements: [
          applicantDataFinder(value, "field"),
          applicantDataFinder(value, "major"),
        ],
        // time: new Date(applicantDataFinder(value, "time")),
      }))}
      onClick={onClick}
    >
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
