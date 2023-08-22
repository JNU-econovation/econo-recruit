"use client";

import { ApplicationQuestion } from "@/src/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationHorizontalLayout = dynamic(
  () => import("./applicationLayout/Horizontal.componet"),
  { ssr: false }
);

const ApplicationVerticalLayout = dynamic(
  () => import("./applicationLayout/Vertical.component")
);

const ApplicationBooleanTextareaLayout = dynamic(
  () => import("./applicationLayout/BooleanTextarea.component")
);

const ApplicationRadioForCheckLayout = dynamic(
  () => import("./applicationLayout/RadioForCheck.component")
);

const ApplicationTimelineLayout = dynamic(
  () => import("./applicationLayout/timeline/Timeline.component")
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
