"use client";

import { ApplicationQuestion } from "@/src/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationHorizontalLayout = dynamic(
  () => import("./ApplicationLayout/Horizontal.componet"),
  { ssr: false }
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

const ApplicationTimelineLayout = dynamic(
  () => import("./ApplicationLayout/timeline/Timeline.component")
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
    timeline: (
      <ApplicationTimelineLayout applicationQuestion={applicationQuestion} />
    ),
  };

  return jsxNode[layout];
};
