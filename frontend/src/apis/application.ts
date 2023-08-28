import { https } from "../functions/axios";

export interface ApplicantReq {
  type: string;
  name: string;
  answer: string;
}

interface ApplicantPostReqError {
  success: boolean;
  status: number;
  code: string;
  reason: string;
  timeStamp: string;
  path: string;
}

export const postApplicant = async (body: ApplicantReq[]) => {
  const { data } = await https.post(`/applicants`, body);

  if (data satisfies ApplicantPostReqError) {
    new Error(data.reason);
  }

  if (data satisfies string) {
    return data;
  }

  return data;
};

export const postApplicantTimeline = async (
  applicantId: string,
  body: number[]
) => {
  const { data } = await https.post(`/applicant${applicantId}/timeline`, body);

  if (data satisfies ApplicantPostReqError) {
    new Error(data.reason);
  }

  if (data satisfies string) {
    return data;
  }

  return data;
};
