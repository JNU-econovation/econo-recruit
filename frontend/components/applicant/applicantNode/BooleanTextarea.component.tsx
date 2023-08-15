import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant";
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
      <Txt typography="h5">{booleantextareaData.title}</Txt>
      <Txt typography="h5" className="block font-normal m-4">
        {selectedValue ? "있다" : "없다"}
      </Txt>
      <Txt typography="h6" className="m-4">
        {selectedValue
          ? booleantextareaData.subtitle[0]
          : booleantextareaData.subtitle[1]}
      </Txt>
      <Txt className="block pt-6 pb-12 ml-4">
        {applicantDataFinder(data, booleantextareaData.value.name)}
      </Txt>
    </>
  );
};

export default ApplicantBooleanTextarea;
