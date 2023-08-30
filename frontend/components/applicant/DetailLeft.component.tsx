import { ApplicantReq } from "@/src/apis/applicant/applicant";
import { FC } from "react";
import ApplicantResource from "./applicantNode/CustomResource.component";
import ApplicantLabel from "./applicantNode/Label.component";
import ApplicantComment from "./applicantNode/comment/Comment.component";
import { applicantDataFinder } from "@/src/functions/finder";

interface ApplicantDetailLeftProps {
  data: ApplicantReq[];
}

const ApplicantDetailLeft: FC<ApplicantDetailLeftProps> = ({ data }) => {
  const postId = applicantDataFinder(data, "id");

  return (
    <>
      <ApplicantResource data={data} />
      <ApplicantLabel postId={postId} />
      <ApplicantComment postId={postId} />
    </>
  );
};

export default ApplicantDetailLeft;
