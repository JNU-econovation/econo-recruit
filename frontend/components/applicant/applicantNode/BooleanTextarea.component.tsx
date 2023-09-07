import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant/applicant";
import { applicantDataFinder } from "@/src/functions/finder";
import { FC } from "react";

interface ApplicantBooleanTextareaProps {
  nodeData: ApplicantNode;
  data: ApplicantReq[];
}

const ApplicantBooleanTextarea: FC<ApplicantBooleanTextareaProps> = ({
  nodeData,
  data,
}) => {
  const booleantextareaData = nodeData as ApplicantBooleanTextareaNode;
  const selectedValue = applicantDataFinder(
    data,
    booleantextareaData.booleanValue.name
  );

  return (
    <>
      <Txt typography="h5" className="block font-normal m-6">
        {selectedValue}
      </Txt>
      <Txt typography="h6" className="m-6">
        {selectedValue
          ? booleantextareaData.subtitle[0]
          : booleantextareaData.subtitle[1]}
      </Txt>
      <Txt className="block pt-6 pb-12 ml-6">
        {applicantDataFinder(data, booleantextareaData.value.name)}
      </Txt>
    </>
  );
};

export default ApplicantBooleanTextarea;