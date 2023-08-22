import { getApplicantLabel } from "@/src/apis/applicant";
import { FC } from "react";

interface ApplicantLabelProps {
  postId: string;
}

const ApplicantLabel: FC<ApplicantLabelProps> = async ({ postId }) => {
  const data = await getApplicantLabel(postId);

  return (
    <>
      {data.map((label, index) => (
        <div key={index}>{label.name}</div>
      ))}
    </>
  );
};

export default ApplicantLabel;
