"use client";

import { FC } from "react";
import Txt from "../common/Txt.component";
import classNames from "classnames";
import { useAtomValue } from "jotai";
import { applicationIndexAtom } from "@/stores/application";
import { ApplicationQuestion } from "@/constants/application/type";
import ApplicationNextButton from "./ApplicationNode/NextButton.component";
import ApplicationHorizontalLayout from "./ApplicationLayout/Horizontal.componet";

interface ApplicationQuestionProps {
  className?: string;
  applicationQuestions: ApplicationQuestion[];
}

const ApplicationQuestion: FC<ApplicationQuestionProps> = ({
  className,
  applicationQuestions,
}) => {
  const applicationIndex = useAtomValue(applicationIndexAtom);
  const applicationQuestion = applicationQuestions[applicationIndex];

  return (
    <article className={classNames(className)}>
      <Txt typography="h1">신입모집 신청</Txt>
      <div className="my-6 h-1 bg-gray-300 w-full"></div>
      <div className="flex gap-6 pr-12">
        {applicationQuestion.direction === "horizontal" ? (
          <ApplicationHorizontalLayout
            applicationQuestion={applicationQuestion}
          />
        ) : (
          ""
        )}
      </div>
      {applicationQuestions.length - 1 > applicationIndex && (
        <div className="translate-x-[calc(100%+1.5rem)] w-[calc(50%-2.3rem)]">
          <ApplicationNextButton
            canNext={true}
            applicationLength={applicationQuestions.length}
          />
        </div>
      )}
    </article>
  );
};

export default ApplicationQuestion;
