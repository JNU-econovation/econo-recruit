import { getApplicant } from "@/src/apis/applicant";
import { APPLICANT } from "@/src/constants/applicant/26";
import { FC, Fragment } from "react";
import { junctionApplicant } from "./Junction.component";

interface ApplicantDetailProps {
  applicantId: string;
}

const getApplicantDetailData = async (applicantId: string) => {
  return await getApplicant(applicantId);
};

const ApplicantDetail: FC<ApplicantDetailProps> = async ({ applicantId }) => {
  const data = await getApplicantDetailData(applicantId);

  APPLICANT;
  return (
    <>
      {APPLICANT.map((node, index) => {
        return <Fragment key={index}>{junctionApplicant(node, data)}</Fragment>;
      })}
    </>
  );
};

export default ApplicantDetail;
