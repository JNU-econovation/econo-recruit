"use client";

import { ApplicationNode } from "@/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationRadio = dynamic(
  () => import("./ApplicationNode/Radio.component")
);

const ApplicationRadioByTwoRank = dynamic(
  () => import("./ApplicationNode/RadioByTwoRank.component")
);

const ApplicationText = dynamic(
  () => import("./ApplicationNode/Text.component")
);

const ApplicationCheckboxWithEtc = dynamic(
  () => import("./ApplicationNode/CheckboxWithEtc.component")
);

const ApplicationTextarea = dynamic(
  () => import("./ApplicationNode/Textarea.component")
);

const ApplicationBar = dynamic(() => import("./ApplicationNode/Bar.component"));

const ApplicationJustText = dynamic(
  () => import("./ApplicationNode/JustText.component")
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
  };

  return jsxNode[applicationNodeData.type];
};
