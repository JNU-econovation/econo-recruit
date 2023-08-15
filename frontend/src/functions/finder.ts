import { ApplicantReq } from "../apis/applicant";

export const applicantDataFinder = (
  applicnatData: ApplicantReq[],
  name: string
) => applicnatData.find((req) => req.name === name)?.answer ?? "";
