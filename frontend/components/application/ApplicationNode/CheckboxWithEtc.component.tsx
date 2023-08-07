import CheckboxGroup, {
  Checkbox,
} from "@/components/common/Checkbox.component";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationCheckboxWithEtcType,
  ApplicationNode,
} from "@/constants/application/type";
import { FC, useState } from "react";

interface ApplicationCheckboxWithEtcProps {
  data: ApplicationNode;
}

const ApplicationCheckboxWithEtc: FC<ApplicationCheckboxWithEtcProps> = ({
  data,
}) => {
  const checkboxWithEtcData = data as ApplicationCheckboxWithEtcType;
  const [isOpenEtc, setIsOpenEtc] = useState(false);
  const [etcValue, setEtcValue] = useState("");
  const [checkboxValue, setCheckboxValue] = useState<string[]>([]);

  return (
    <>
      {checkboxWithEtcData.title && (
        <div className="mb-2">
          <Txt typography="h6">{`${checkboxWithEtcData.title}${
            checkboxWithEtcData.require ? "*" : ""
          }`}</Txt>
        </div>
      )}
      {checkboxWithEtcData.subtitle && (
        <div className="mb-2">
          <Txt>{checkboxWithEtcData.subtitle}</Txt>
        </div>
      )}
      <div className="pt-2">
        <CheckboxGroup
          checkboxList={checkboxWithEtcData.value}
          name={checkboxWithEtcData.name}
          onChange={(e) => {
            setCheckboxValue((prev) => {
              return prev.includes(e.target.value)
                ? prev.filter((value) => value !== e.target.value)
                : [...prev, e.target.value];
            });
          }}
          value={checkboxValue}
          splitNumber={3}
        />
        <div className="grid grid-cols-3 font-semibold gap-2">
          <Checkbox
            className="mt-2"
            name={checkboxWithEtcData.name}
            value="기타"
            checked={isOpenEtc}
            label="기타"
            onChange={() => setIsOpenEtc((prev) => !prev)}
          />
          {isOpenEtc && (
            <input
              type="text"
              className="col-span-2 border border-gray-300 rounded-md p-4 mt-2"
              value={etcValue}
              onChange={(e) => setEtcValue(e.target.value)}
              placeholder="내용을 입력해주세요."
            />
          )}
        </div>
      </div>
    </>
  );
};

export default ApplicationCheckboxWithEtc;
