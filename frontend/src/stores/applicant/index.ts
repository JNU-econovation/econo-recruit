import { atom } from "jotai";

const applicantQuestions = require(`@/src/constants/applicant/26`)
  .APPLICANT as ApplicantNode[];

export const applicantQuestionsAtom = atom(applicantQuestions);
