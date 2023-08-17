import { ApplicantReq } from "@/src/apis/applicant";
import { FC } from "react";
import ApplicantResource from "./applicantNode/CustomResource.component";

interface ApplicantDetailLeftProps {
  data: ApplicantReq[];
}

const ApplicantDetailLeft: FC<ApplicantDetailLeftProps> = ({ data }) => {
  return (
    <>
      <ApplicantResource data={data} />
    </>
  );
};

export default ApplicantDetailLeft;
