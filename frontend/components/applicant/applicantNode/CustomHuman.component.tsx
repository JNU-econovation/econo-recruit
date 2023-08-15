import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant";
import { applicantDataFinder } from "@/src/functions/finder";
import { FC } from "react";

interface ApplicantCustomHumanProps {
  nodeData: ApplicantNode;
  data: ApplicantReq[];
}

const ApplicantCustomHuman: FC<ApplicantCustomHumanProps> = ({
  nodeData,
  data,
}) => {
  const customHumanData = nodeData as ApplicantCustomHumanNode;

  return (
    <>
      <Txt typography="h6">{customHumanData.title}</Txt>
      <div className="flex items-center py-6">
        <Txt typography="h1" className="px-6">
          {applicantDataFinder(data, customHumanData.value.hunamName.name)}
        </Txt>
        <div className="h-full flex flex-col gap-2 justify-between">
          <Txt>
            {applicantDataFinder(data, customHumanData.value.humanPhone.name)}
          </Txt>
          <Txt>
            {applicantDataFinder(data, customHumanData.value.humanEmail.name)}
          </Txt>
          <Txt>
            {customHumanData.value.humanEtc
              .map((etc) => applicantDataFinder(data, etc.name))
              .join(" ")}
          </Txt>
        </div>
      </div>
    </>
  );
};

export default ApplicantCustomHuman;
