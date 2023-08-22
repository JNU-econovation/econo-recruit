import { ApplicantReq } from "@/src/apis/applicant";
import { FC } from "react";
import ApplicantResource from "./applicantNode/CustomResource.component";
import ApplicantLabel from "./applicantNode/Label.component";

interface ApplicantDetailLeftProps {
  data: ApplicantReq[];
  postId: string;
}

const ApplicantDetailLeft: FC<ApplicantDetailLeftProps> = ({
  data,
  postId,
}) => {
  return (
    <>
      <ApplicantResource data={data} />
      <ApplicantLabel postId={postId} />
    </>
  );
};

export default ApplicantDetailLeft;
