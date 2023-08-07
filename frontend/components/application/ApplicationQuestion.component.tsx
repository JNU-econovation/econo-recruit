"use client";

import { FC } from "react";
import Txt from "../common/Txt.component";
import classNames from "classnames";
import { useAtomValue } from "jotai";
import { applicationIndexAtom } from "@/stores/application";
import { ApplicationQuestion } from "@/constants/application/type";
import ApplicationNextButton from "./ApplicationNode/ApplicationNextButton.component";
import { junctionQuestion } from "./ApplicationJunction.component";

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
      <div className="flex pr-12">
        <div className="flex-1">
          <div className="mb-4 flex gap-2">
            <Txt typography="h6">{`${applicationQuestion.id}. `}</Txt>
            <Txt typography="h6">{`${applicationQuestion.title}${
              applicationQuestion.require ? "*" : ""
            }`}</Txt>
          </div>
          {applicationQuestion.subtitle && (
            <div className="pl-6">
              <Txt className="text-sm">{applicationQuestion.subtitle}</Txt>
            </div>
          )}
        </div>
        <div className="flex-1">
          {applicationQuestion.nodes.map((node, index) => {
            return (
              <div key={index} className="my-4">
                {junctionQuestion(node)}
              </div>
            );
          })}
          {applicationQuestions.length - 1 > applicationIndex && (
            <ApplicationNextButton
              canNext={true}
              applicationLength={applicationQuestions.length}
            />
          )}
        </div>
      </div>
    </article>
  );
};

export default ApplicationQuestion;
