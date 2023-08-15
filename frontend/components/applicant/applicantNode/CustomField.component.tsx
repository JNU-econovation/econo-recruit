import Txt from "@/components/common/Txt.component";
import { ApplicantReq } from "@/src/apis/applicant";
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
    </>
  );
};

export default ApplicantCustomField;
