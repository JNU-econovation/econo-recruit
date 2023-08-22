import { ApplicantReq } from "../apis/applicant";

export const applicantDataFinder = (
  applicantData: ApplicantReq[],
  name: string
) => applicantData.find((req) => req.name === name)?.answer ?? "";
