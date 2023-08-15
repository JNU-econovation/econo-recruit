import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant";
import { applicantDataFinder } from "@/src/functions/finder";
import { FC } from "react";

interface ApplicantCustomFieldProps {
  nodeData: ApplicantNode;
  data: ApplicantReq[];
}

const ApplicantCustomField: FC<ApplicantCustomFieldProps> = ({
  nodeData,
  data,
}) => {
  const customFieldData = nodeData as ApplicantCustomFieldNode;

  return (
    <>
      <Txt typography="h6">{customFieldData.title}</Txt>
      <div className="flex items-center pt-6 pb-12">
        <Txt typography="h1" className="px-6">
          {applicantDataFinder(data, customFieldData.value.name)}
        </Txt>
        <div className="h-full flex flex-col gap-2 justify-between">
          {customFieldData.subValue.map((subValue, index) => (
            <Txt key={index}>{`${subValue.title} ${applicantDataFinder(
              data,
              subValue.name
            )}`}</Txt>
          ))}
        </div>
      </div>
    </>
  );
};

export default ApplicantCustomField;
