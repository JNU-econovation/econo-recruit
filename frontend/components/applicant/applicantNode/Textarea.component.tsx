import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant";
import { applicantDataFinder } from "@/src/functions/finder";
import { FC } from "react";

interface ApplicantTextareaProps {
  nodeData: ApplicantNode;
  data: ApplicantReq[];
}

const ApplicantTextarea: FC<ApplicantTextareaProps> = ({ nodeData, data }) => {
  const textareaData = nodeData as ApplicantTextareaNode;

  return (
    <>
      <Txt typography="h6">{textareaData.title}</Txt>
      <Txt className="block pt-6 pb-12 ml-4">
        {applicantDataFinder(data, textareaData.value.name)}
      </Txt>
    </>
  );
};

export default ApplicantTextarea;
