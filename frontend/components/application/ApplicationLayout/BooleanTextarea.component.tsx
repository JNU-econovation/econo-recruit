"use client";

import RadioGroup from "@/components/common/Radio.component";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationBooleanTextarea,
  ApplicationQuestion,
} from "@/constants/application/type";
import { FC, useState } from "react";

interface ApplicationBooleanTextareaProps {
  applicationQuestion: ApplicationQuestion;
}

const ApplicationBooleanTextarea: FC<ApplicationBooleanTextareaProps> = ({
  applicationQuestion,
}) => {
  const booleanTextareaData = applicationQuestion
    .nodes[0] as ApplicationBooleanTextarea;
  const [selectedValue, setSelectedValue] = useState("init");
  const [textValue, setTextValue] = useState("");

  return (
    <div className="w-full flex-1">
      <div className="flex gap-6">
        <div className="flex-1">
          <div className="mb-4 flex gap-2">
            <Txt typography="h6">{`${applicationQuestion.id}. `}</Txt>
            <Txt typography="h6" className="break-keep">{`${
              applicationQuestion.title
            }${applicationQuestion.require ? "*" : ""}`}</Txt>
          </div>
          {applicationQuestion.subtitle && (
            <div className="pl-6">
              <Txt className="text-sm break-keep">
                {applicationQuestion.subtitle}
              </Txt>
            </div>
          )}
        </div>
        <div className="flex-1">
          <RadioGroup
            name={booleanTextareaData.name}
            onChange={(e) => setSelectedValue(e.target.value)}
            radioList={booleanTextareaData.value}
            value={selectedValue}
          />
        </div>
      </div>
      {booleanTextareaData.subNodes.map((node, index) => {
        const findByIndex = booleanTextareaData.value.findIndex(
          (value) => value === selectedValue
        );
        if (findByIndex !== index) return <></>;

        return (
          <div key={index} className="flex gap-6">
            <div className="flex-1">
              <div className="pl-8">
                <Txt typography="h6" className="mb-4 block break-keep">
                  {`${index + 1}. ${node.title}`}
                </Txt>
                <Txt className="pl-4 block break-keep">{node.subtitle}</Txt>
              </div>
            </div>
            <div className="flex-1">
              <textarea
                className="my-2 border rounded-lg p-4 w-full resize-none"
                rows={20}
                name={node.name}
                value={textValue}
                onChange={(e) => setTextValue(e.target.value)}
              />
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ApplicationBooleanTextarea;
