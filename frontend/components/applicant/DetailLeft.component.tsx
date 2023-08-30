import { ApplicantReq } from "@/src/apis/applicant/applicant";
import { FC } from "react";
import ApplicantResource from "./applicantNode/CustomResource.component";
import ApplicantLabel from "./applicantNode/Label.component";
import { applicantDataFinder } from "@/src/functions/finder";
import dynamic from "next/dynamic";

interface ApplicantDetailLeftProps {
  data: ApplicantReq[];
}

const ApplicantComment = dynamic(
  () => import("./applicantNode/comment/Comment.component"),
  { ssr: false }
);

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
