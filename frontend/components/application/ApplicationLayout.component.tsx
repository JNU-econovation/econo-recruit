"use client";

import { ApplicationQuestion } from "@/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationHorizontalLayout = dynamic(
  () => import("./ApplicationLayout/Horizontal.componet")
);

const ApplicationVerticalLayout = dynamic(
  () => import("./ApplicationLayout/Vertical.component")
);

const ApplicationBooleanTextareaLayout = dynamic(
  () => import("./ApplicationLayout/BooleanTextarea.component")
);

const ApplicationRadioForCheckLayout = dynamic(
  () => import("./ApplicationLayout/RadioForCheck.component")
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
      <ApplicationBooleanTextareaLayout
        applicationQuestion={applicationQuestion}
      />
    ),
    radioForCheck: (
      <ApplicationRadioForCheckLayout
        applicationQuestion={applicationQuestion}
      />
    ),
  };

  return jsxNode[layout];
};
