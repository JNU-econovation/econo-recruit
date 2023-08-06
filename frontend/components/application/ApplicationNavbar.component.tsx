"use client";

import { applicationIndexAtom } from "@/stores/application";
import { useAtom } from "jotai";
import { FC } from "react";
import classNames from "classnames";
import { APPLICATION } from "@/constants/application/25";
import Txt from "../common/Txt.component";

interface ApplicationNavbarProps {
  applicationQuestions: ApplicationQuestion[];
  className?: string;
}

const ApplicationNavbar: FC<ApplicationNavbarProps> = ({
  applicationQuestions,
  className,
}) => {
  const [applicationIndex, setApplicationIndex] = useAtom(applicationIndexAtom);

  return (
    <nav className={classNames("pl-12 w-full h-full", className)}>
      {applicationQuestions.map((question, index) => (
        <button
          className={"text-left p-4 relative"}
          onClick={() => setApplicationIndex(index)}
          key={question.id}
        >
          {/* 마지막 선은 그리지 않기 */}
          {index !== APPLICATION.length - 1 && (
            <div
              className={classNames(
                "absolute border-l-2 h-full left-[3px] top-8 -z-10",
                applicationIndex - 1 >= index
                  ? "border-black"
                  : "border-gray-300"
              )}
            ></div>
          )}
          <Txt
            className={classNames(
              "relative transition-all before:h-2 before:w-2 before:rounded-full before:absolute before:translate-y-full before:-translate-x-4",
              applicationIndex >= index
                ? "before:bg-black text-black"
                : "before:bg-gray-300 text-gray-300"
            )}
          >
            {question.title}
          </Txt>
        </button>
      ))}
    </nav>
  );
};

export default ApplicationNavbar;
