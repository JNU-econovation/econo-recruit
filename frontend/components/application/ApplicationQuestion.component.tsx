"use client";

import { FC } from "react";
import Txt from "../common/Txt.component";
import classNames from "classnames";
import { useAtomValue } from "jotai";
import { applicationIndexAtom } from "@/stores/application";

interface ApplicationQuestionProps {
  className?: string;
  applicationQuestion: ApplicationQuestion[];
}

const ApplicationQuestion: FC<ApplicationQuestionProps> = ({
  className,
  applicationQuestion,
}) => {
  const applicationIndex = useAtomValue(applicationIndexAtom);

  return (
    <article className={classNames(className)}>
      <Txt typography="h1">신입모집 신청</Txt>
      <div className="my-6 h-1 bg-gray-300 w-full"></div>
    </article>
  );
};

export default ApplicationQuestion;
