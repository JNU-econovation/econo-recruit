"use client";

import { FC } from "react";
import Txt from "../common/Txt.component";
import classNames from "classnames";
import { useAtomValue } from "jotai";
import { applicationIndexAtom } from "@/stores/application";
import { ApplicationQuestion } from "@/constants/application/type";
import ApplicationNextButton from "./ApplicationNode/NextButton.component";
import ApplicationHorizontalLayout from "./ApplicationLayout/Horizontal.componet";
import ApplicationVerticalLayout from "./ApplicationLayout/Vertical.component";
import { applicationLayout } from "./ApplicationLayout.component";

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
      {applicationLayout(applicationQuestion.direction, applicationQuestion)}
      <div className="translate-x-[calc(100%+1.5rem)] w-[calc(50%-2.3rem)]">
        {applicationQuestions.length - 1 > applicationIndex ? (
          <ApplicationNextButton
            canNext={true}
            applicationLength={applicationQuestions.length}
          />
        ) : (
          <ApplicationNextButton
            canNext={true}
            applicationLength={applicationQuestions.length}
            isLast={true}
            beforeCheckCallback={() => false}
          />
        )}
      </div>
    </article>
  );
};

export default ApplicationQuestion;
