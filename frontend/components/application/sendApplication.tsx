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

  Object.entries(node).map(([key, value]) => {
    if (Array.isArray(value)) {
      value.forEach((element) => {
        extractApplicantData(element, applicationData);
      });
      return;
    }
    if (key === "name" && value !== "timeline") {
      if ("require" in node) {
        if (localStorage.get(value, "").length === 0 && node.require) {
          throw new Error(`지원서 작성이 완료되지 않았습니다. ${value}`);
        }
        applicationData.add({
          name: value,
          answer: JSON.stringify(localStorage.get(value, "")),
        });
      }
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
    applicationData.add({
      name: "generation",
      answer: `${CURRENT_GENERATION}`,
    });
    applicationData.add({
      name: "uploadDate",
      answer: `${new Date().getTime()}`,
    });
    const applicantId = await postApplicant(Array.from(applicationData));
    await postApplicantTimeline(applicantId, localStorage.get("timeline", []));
  } catch (e) {
    alert(`지원서 제출에 실패했습니다. 관리자에게 문의해주세요.\n ${e}`);
    return false;
  }

  window.localStorage.clear();
  window.history.pushState(null, "", "/application/done");
  window.history.go(0);
  return true;
};
