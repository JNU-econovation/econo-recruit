import Txt from "@/components/common/Txt.component";
import { ApplicationQuestion } from "@/constants/application/type";
import { FC } from "react";
import { junctionQuestion } from "../ApplicationJunction.component";

interface ApplicationHorizontalLayoutProps {
  applicationQuestion: ApplicationQuestion;
}

const ApplicationHorizontalLayout: FC<ApplicationHorizontalLayoutProps> = ({
  applicationQuestion,
}) => {
  return (
    <>
      <div className="flex-1">
        <div className="mb-4 flex gap-2">
          <Txt typography="h6">{`${applicationQuestion.id}. `}</Txt>
          <Txt typography="h6" className="break-words">{`${
            applicationQuestion.title
          }${applicationQuestion.require ? "*" : ""}`}</Txt>
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
            <div key={index} className="mb-4">
              {junctionQuestion(node)}
            </div>
          );
        })}
      </div>
    </>
  );
};

export default ApplicationHorizontalLayout;
