"use client";

import Txt from "@/components/common/Txt.component";
import {
  ApplicationNode,
  ApplicationTextarea,
} from "@/constants/application/type";
import classNames from "classnames";
import { FC, useId, useState } from "react";

interface ApplicationTexareaProps {
  data: ApplicationNode;
}

const ApplicationTexarea: FC<ApplicationTexareaProps> = ({ data }) => {
  const textData = data as ApplicationTextarea;
  const id = useId();
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
      <textarea
        className={classNames("my-2 border rounded-lg p-4 w-full resize-none")}
        rows={20}
        id={id}
        name={textData.name}
        value={value}
        onChange={() => setValue(value)}
      />
    </>
  );
};

export default ApplicationTexarea;
