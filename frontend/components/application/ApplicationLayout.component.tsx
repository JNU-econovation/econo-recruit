"use client";

import { ApplicationQuestion } from "@/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationHorizontalLayout = dynamic(
  () => import("./ApplicationLayout/Horizontal.componet")
);

const ApplicationVerticalLayout = dynamic(
  () => import("./ApplicationLayout/Vertical.component")
);

const ApplicationBooleanTextarea = dynamic(
  () => import("./ApplicationLayout/BooleanTextarea.component")
);

export const applicationLayout = (
  layout: ApplicationQuestion["direction"],
  applicationQuestion: ApplicationQuestion
) => {
  const jsxNode = {
    horizontal: (
      <ApplicationHorizontalLayout applicationQuestion={applicationQuestion} />
    ),
    vertical: (
      <ApplicationVerticalLayout applicationQuestion={applicationQuestion} />
    ),
    booleanTextarea: (
      <ApplicationBooleanTextarea applicationQuestion={applicationQuestion} />
    ),
  };

  return jsxNode[layout];
};
