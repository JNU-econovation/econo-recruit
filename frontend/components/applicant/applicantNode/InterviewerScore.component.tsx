"use client";

import { INTERVIEW_SCORE_KEYWOARD } from "@/src/constants/applicant/26";
import Image from "next/image";
import { useState } from "react";

const ApplicantInterviewerScore = () => {
  const [isShow, setIsShow] = useState(false);

  const onClick = () => {
    setIsShow((prev) => !prev);
  };

  return (
    <>
      {!isShow ? (
        <button
          onClick={onClick}
          className="text-[#666666] text-sm my-4 underline-offset-2 underline"
        >
          면접관 점수 입력
        </button>
      ) : (
        <div className="flex flex-col">
          <div className="flex w-full justify-between items-start">
            <button
              onClick={onClick}
              className="text-[#666666] text-sm my-4 underline-offset-2 underline"
            >
              면접관 점수 입력
            </button>
            <div className="flex border rounded-full justify-center items-center p-2">
              <input
                className="outline-none w-16 text-sm placeholder:text-[#4F4F4F] ml-4"
                type="text"
                placeholder="이름입력"
              />
              <button className="grayscale h-6 w-6 opacity-60">
                <Image
                  src={
                    require("/public/icons/arrow.forward.circle.fill.svg")
                      .default
                  }
                  alt="interview score append"
                />
              </button>
            </div>
          </div>
          <div className="flex justify-between text-sm text-[#4F4F4F] mb-12 mt-4 px-4">
            {INTERVIEW_SCORE_KEYWOARD.map((keyword) => (
              <div key={keyword.name} className="flex flex-col items-center">
                <input placeholder="-" className="placeholder-gray-500 border-none text-2xl outline-none w-16 bg-slate-200 " type="number"/>
                <div>{keyword.title}</div>
              </div>
            ))}
          </div>
        </div>
      )}
    </>
  );
};

export default ApplicantInterviewerScore;
