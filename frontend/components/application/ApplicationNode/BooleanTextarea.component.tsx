"use client";

import RadioGroup from "@/components/common/Radio.component";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationBooleanTextarea,
  ApplicationNode,
} from "@/constants/application/type";
import { FC, useState } from "react";

interface ApplicationBooleanTextareaProps {
  data: ApplicationNode;
}

const ApplicationBooleanTextarea: FC<ApplicationBooleanTextareaProps> = ({
  data,
}) => {
  const textData = data as ApplicationBooleanTextarea;
  const [isSelected, setIsSelected] = useState<string>("");
  const [value, setValue] = useState("");

  return (
    <>
      {textData.title && (
        <label>
          <Txt typography="h6">{`${textData.title}${
            textData.require ? "*" : ""
          }`}</Txt>
          {textData.subtitle && <Txt>{` ${textData.subtitle}`}</Txt>}
        </label>
      )}
      <RadioGroup
        name={textData.name + "bool"}
        value={isSelected}
        onChange={(e) => setIsSelected(e.target.value)}
        radioList={["있다", "없다"]}
      />

      {/* {textData.subNodes.map((subNode) => {
        <textarea
          className={classNames(
            "my-2 border rounded-lg p-4 w-full resize-none"
          )}
          rows={20}
          name={subNode.name}
          value={value}
          onChange={() => setValue(value)}
        />;
      })} */}
    </>
  );
};

export default ApplicationBooleanTextarea;
