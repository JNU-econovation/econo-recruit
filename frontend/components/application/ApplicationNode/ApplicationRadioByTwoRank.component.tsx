import RadioGroup from "@/components/common/Radio";
import Txt from "@/components/common/Txt.component";
import {
  ApplicationNode,
  ApplicationRadioByTwoRank,
} from "@/constants/application/type";
import { FC, useState } from "react";

interface ApplicationRadioByTwoRankProps {
  data: ApplicationNode;
}

const ApplicationRadioByTwoRank: FC<ApplicationRadioByTwoRankProps> = ({
  data,
}) => {
  const radioByTwoRankData = data as ApplicationRadioByTwoRank;
  const firstNode = radioByTwoRankData.subNodes[0];
  const secondNode = radioByTwoRankData.subNodes[1];
  const [firstValue, setFirstValue] = useState("init");
  const [secondValue, setSecondValue] = useState("");

  return (
    <>
      {radioByTwoRankData.title && (
        <div className="mb-2">
          <Txt typography="h6">{`${radioByTwoRankData.title} ${
            radioByTwoRankData.require ? "*" : ""
          }`}</Txt>
        </div>
      )}
      <Txt className="my-4 block">{firstNode.title}</Txt>
      <RadioGroup
        name={firstNode.name}
        radioList={firstNode.value}
        splitNumber={firstNode.splitNumber}
        value={firstValue}
        isSpaned={true}
        disableValue={secondValue}
        onChange={(e) => {
          setFirstValue(e.target.value);
          setSecondValue("");
        }}
      />
      <Txt className="my-4 block">{secondNode.title}</Txt>
      <RadioGroup
        name={secondNode.name}
        radioList={secondNode.value}
        splitNumber={secondNode.splitNumber}
        value={secondValue}
        isSpaned={true}
        disableValue={firstValue}
        onChange={(e) => {
          setSecondValue(e.target.value);
        }}
      />
    </>
  );
};

export default ApplicationRadioByTwoRank;
