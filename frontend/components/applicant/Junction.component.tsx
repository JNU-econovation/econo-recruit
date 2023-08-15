import { ApplicantReq } from "@/src/apis/applicant";
import dynamic from "next/dynamic";

const ApplicantCustomField = dynamic(
  () => import("./applicantNode/CustomField.component")
);

const ApplicantCustomHuman = dynamic(
  () => import("./applicantNode/CustomHuman.component")
);

const ApplicantShortSplit = dynamic(
  () => import("./applicantNode/ShortSplit.component")
);

export const junctionApplicant = (
  applicantNodeData: ApplicantNode,
  applicantData: ApplicantReq[]
) => {
  const jsxNode = {
    customField: (
      <ApplicantCustomField nodeData={applicantNodeData} data={applicantData} />
    ),
    customHuman: (
      <ApplicantCustomHuman nodeData={applicantNodeData} data={applicantData} />
    ),
    shortSplit: (
      <ApplicantShortSplit nodeData={applicantNodeData} data={applicantData} />
    ),
    textarea: <></>,
    booleanTextarea: <></>,
    timeline: <></>,
  };

  return jsxNode[applicantNodeData.type];
};
