"use client";

import RadioGroup from "@/components/common/Radio.component";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationNode,
  ApplicationRadio,
} from "@/constants/application/type";
import { useLocalStorage } from "@/hooks/useLocalstorage.hook";
import { FC } from "react";

interface ApplicationRadioProps {
  data: ApplicationNode;
}

const ApplicationRadio: FC<ApplicationRadioProps> = ({ data }) => {
  const radioData = data as ApplicationRadio;
  const [value, setValue] = useLocalStorage<string>(radioData.name, "");

  return (
    <>
      {radioData.title && (
        <div className="mb-2">
          <Txt typography="h6">{radioData.title}</Txt>
          {radioData.require && <Txt>*</Txt>}
        </div>
      )}
      {radioData.subtitle && (
        <div className="mb-2">
          <Txt>{radioData.subtitle}</Txt>
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
    </>
  );
};

export default ApplicationRadio;
