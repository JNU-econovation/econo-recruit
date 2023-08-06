"use client";

import RadioGroup from "@/components/common/Radio";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationNode,
  ApplicationRadio,
} from "@/constants/application/type";
import { FC, useState } from "react";

interface ApplicationRadioProps {
  data: ApplicationNode;
}

const ApplicationRadio: FC<ApplicationRadioProps> = ({ data }) => {
  const [value, setValue] = useState<string>("");
  const radioData = data as ApplicationRadio;

  return (
    <div>
      {radioData.title && (
        <div className="mb-2">
          <Txt typography="h6">{radioData.title}</Txt>
          {radioData.require && <Txt>*</Txt>}
        </div>
      )}
      {radioData.subtitle && (
        <div className="mb-2">
          <Txt className="text-gray-400">{radioData.subtitle}</Txt>
        </div>
      )}
      <RadioGroup
        name={radioData.name}
        radioList={radioData.value}
        value={value}
        onChange={(e) => {
          setValue(e.target.value);
        }}
      />
    </div>
  );
};

export default ApplicationRadio;
