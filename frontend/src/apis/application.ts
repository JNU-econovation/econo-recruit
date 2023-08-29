import { https } from "../functions/axios";

export interface ApplicantReq {
  name: string;
  answer: string;
}

export const postApplicant = async (body: ApplicantReq[]) => {
  const { data } = await https.post(`/applicants`, body);

  if (data satisfies string) {
    return data;
  }

  return data;
};

export const postApplicantTimeline = async (
  applicantId: string,
  body: number[]
) => {
  const { data } = await https.post(`/applicant/${applicantId}/timeline`, body);

  if (data satisfies string) {
    return data;
  }

  return data;
};
