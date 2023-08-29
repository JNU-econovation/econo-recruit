"use client";

import { ApplicantReq } from "@/src/apis/applicant/applicant";
import { postApplicant, postApplicantTimeline } from "@/src/apis/application";
import { CURRENT_GENERATION } from "@/src/constants";
import { ApplicationQuestion } from "@/src/constants/application/type";
import { localStorage } from "@/src/functions/localstorage";

// 깊은 탐색을 통해 지원자가 작성한 데이터를 추출하는 함수
const extractApplicantData = (
  node: Object,
  applicationData: Set<ApplicantReq>
) => {
  if (node === null) return;
  if (Array.isArray(node)) {
    node.forEach((element) => {
      extractApplicantData(element, applicationData);
    });
    return;
  }

  return Object.entries(node).map(([key, value]) => {
    if (Array.isArray(value)) {
      value.forEach((element) => {
        extractApplicantData(element, applicationData);
      });
      return;
    }
    if (key === "name") {
      value !== "timeline" &&
        applicationData.add({
          name: value,
          answer: JSON.stringify(localStorage.get(value, "")),
        });
    }
  });
};

export const postApplication = async () => {
  const applicationData = new Set<ApplicantReq>();

  const generation = CURRENT_GENERATION;
  const applicationQuestions =
    require(`@/src/constants/application/${generation}.ts`)
      .APPLICATION as ApplicationQuestion[];

  try {
    extractApplicantData(applicationQuestions, applicationData);
    const applicantId = await postApplicant(Array.from(applicationData));
    await postApplicantTimeline(applicantId, localStorage.get("timeline", []));
  } catch (e) {
    alert("지원서 제출에 실패했습니다. 관리자에게 문의해주세요.");
    return false;
  }

  window.localStorage.clear();

  return true;
};
