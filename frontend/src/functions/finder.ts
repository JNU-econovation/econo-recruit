import { ApplicantReq } from "../apis/applicant/applicant";

export const applicantDataFinder = (
  applicantData: ApplicantReq[],
  name: string
) => {
  const data = applicantData.find((req) => req.name === name)?.answer ?? "";
  if (name === "id") return data;

  return data === "" ? "" : JSON.parse(data);
};
