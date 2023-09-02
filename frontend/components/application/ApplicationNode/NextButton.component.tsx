"use client";

import useApplicationIndexControll from "@/src/hooks/useApplicationIndexControll.hook";
import { FC } from "react";
import { postApplication } from "../sendApplication";
import { useAtomValue } from "jotai";
import { applicationDataAtom } from "@/src/stores/application";
import { applicantQuestionsAtom } from "@/src/stores/applicant";
import { ApplicationQuestion } from "@/src/constants/application/type";

interface ApplicationNextButtonProps {
  canNext: boolean;
  applicationLength: number;
  beforeCheckCallback?: () => boolean;
  isLast?: boolean;
}

const ApplicationNextButton: FC<ApplicationNextButtonProps> = ({
  canNext,
  beforeCheckCallback,
  applicationLength,
  isLast = false,
}) => {
  const { applicationIndex, goNextIndex, goPrevIndex } =
    useApplicationIndexControll();
  const nextButtonClassName =
    "flex-1 rounded-md flex justify-center items-center p-4";
  const applicationData = useAtomValue(applicationDataAtom);
  const applicationQuestion = useAtomValue(applicantQuestionsAtom);

  const applicationName = new Set<string>();

  const getApplicationName = (
    node: { [key: string]: any } | ApplicationQuestion[],
    applicationName: Set<string>
  ) => {
    if (node === undefined) return;
    if (Array.isArray(node)) {
      node.forEach((element) => {
        getApplicationName(element, applicationName);
      });
      return;
    }

    Object.entries(node).map(([key, value]) => {
      if (Array.isArray(value)) {
        value.forEach((element) => {
          getApplicationName(element, applicationName);
        });
        return;
      }
      if (key === "name" && value !== "timeline") {
        if ("require" in node) {
          if (node.require) {
            console.log(value);
          }
        }
      }
    });
  };

  getApplicationName(applicationData[applicationIndex], applicationName);

  const beforeCheck = () => {
    if (beforeCheckCallback) {
      if (!beforeCheckCallback()) {
        return;
      }
    }
    applicationQuestion[applicationIndex];
  };

  return (
    <div className="flex gap-2 my-4">
      <button
        className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
        onClick={goPrevIndex}
      >
        이전
      </button>
      <button
        onClick={
          isLast
            ? () => postApplication(applicationData)
            : () => {
                beforeCheck();
                goNextIndex();
              }
        }
        disabled={!canNext}
        className={
          canNext
            ? nextButtonClassName + " bg-[#303030] text-white"
            : nextButtonClassName + " bg-[#EFEFEF] text-[#C8C8C8]"
        }
      >
        {isLast
          ? "제출하기"
          : `다음(${applicationIndex + 1}/${applicationLength})`}
      </button>
    </div>
  );
};

export default ApplicationNextButton;
