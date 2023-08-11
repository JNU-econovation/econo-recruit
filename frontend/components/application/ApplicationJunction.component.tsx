"use client";

import { ApplicationNode } from "@/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationRadio = dynamic(
  () => import("./applicationNode/Radio.component"),
  { ssr: false }
);

const ApplicationRadioByTwoRank = dynamic(
  () => import("./applicationNode/RadioByTwoRank.component"),
  { ssr: false }
);

const ApplicationText = dynamic(
  () => import("./applicationNode/Text.component"),
  { ssr: false }
);

const ApplicationCheckboxWithEtc = dynamic(
  () => import("./applicationNode/CheckboxWithEtc.component"),
  { ssr: false }
);

const ApplicationTextarea = dynamic(
  () => import("./applicationNode/Textarea.component"),
  { ssr: false }
);

const ApplicationBar = dynamic(
  () => import("./applicationNode/Bar.component"),
  { ssr: false }
);

const ApplicationJustText = dynamic(
  () => import("./applicationNode/JustText.component"),
  { ssr: false }
);

export const junctionQuestion = (applicationNodeData: ApplicationNode) => {
  const jsxNode = {
    radio: <ApplicationRadio data={applicationNodeData} />,
    radioByTwoRank: <ApplicationRadioByTwoRank data={applicationNodeData} />,
    radioForCheck: <></>,
    text: <ApplicationText data={applicationNodeData} />,
    textarea: <ApplicationTextarea data={applicationNodeData} />,
    booleanTextarea: <></>,
    bar: <ApplicationBar />,
    justText: <ApplicationJustText data={applicationNodeData} />,
    checkboxWithEtc: <ApplicationCheckboxWithEtc data={applicationNodeData} />,
    timeline: <></>,
  };

  return jsxNode[applicationNodeData.type];
};
