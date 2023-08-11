"use client";

import { ApplicationQuestion } from "@/constants/application/type";
import dynamic from "next/dynamic";
import ApplicationTimelineLayout from "./applicationLayout/Timeline.component";

const ApplicationHorizontalLayout = dynamic(
  () => import("./applicationLayout/Horizontal.componet"),
  { ssr: false }
);

const ApplicationVerticalLayout = dynamic(
  () => import("./applicationLayout/Vertical.component"),
  { ssr: false }
);

const ApplicationBooleanTextareaLayout = dynamic(
  () => import("./applicationLayout/BooleanTextarea.component"),
  { ssr: false }
);

const ApplicationRadioForCheckLayout = dynamic(
  () => import("./applicationLayout/RadioForCheck.component"),
  { ssr: false }
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
    timeline: <ApplicationTimelineLayout />,
  };

  return jsxNode[layout];
};
