"use client";

import { applicationIndexAtom } from "@/stores/application";
import { useAtom } from "jotai";
import { FC } from "react";
import classNames from "classnames";
import { APPLICATION } from "@/constants/application/25";

interface ApplicationNavbarProps {
  generation: string;
}

const ApplicationNavbar: FC<ApplicationNavbarProps> = ({ generation }) => {
  const [applicationIndex, setApplicationIndex] = useAtom(applicationIndexAtom);
  const ApplicationQuestion =
    require(`@/constants/application/${generation}.ts`)
      .APPLICATION as ApplicationQuestion[];

  return (
    <section className="pl-12 w-full h-full">
      {ApplicationQuestion.map((question, index) => (
        <button
          className={classNames(
            "text-left block relative p-4 transition-all before:h-2 before:w-2 before:rounded-full before:absolute before:top-1/2 before:-translate-y-1/2 before:-translate-x-4",
            applicationIndex >= index
              ? "before:bg-black text-black"
              : "before:bg-gray-300 text-gray-300"
          )}
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
          {question.title}
        </button>
      ))}
    </section>
  );
};

export default ApplicationNavbar;
