import { ApplicationNode } from "@/constants/application/type";
import dynamic from "next/dynamic";

const ApplicationRadio = dynamic(
  () => import("./ApplicationNode/ApplicationRadio.component")
);

const ApplicationRadioByTwoRank = dynamic(
  () => import("./ApplicationNode/ApplicationRadioByTwoRank.component")
);

const ApplicationText = dynamic(
  () => import("./ApplicationNode/ApplicationText.component")
);

const ApplicationCheckboxWithEtc = dynamic(
  () => import("./ApplicationNode/ApplicationCheckboxWithEtc.component")
);

const ApplicationTextarea = dynamic(
  () => import("./ApplicationNode/ApplicationTextarea.component")
);

export const junctionQuestion = (applicationNodeData: ApplicationNode) => {
  const jsxNode = {
    radio: <ApplicationRadio data={applicationNodeData} />,
    radioByTwoRank: <ApplicationRadioByTwoRank data={applicationNodeData} />,
    radioByLayer: "",
    text: <ApplicationText data={applicationNodeData} />,
    textarea: <ApplicationTextarea data={applicationNodeData} />,
    booleanTextBox: "",
    bar: "",
    justText: "",
    checkbox: "",
    checkboxWithEtc: <ApplicationCheckboxWithEtc data={applicationNodeData} />,
  };

  return jsxNode[applicationNodeData.type];
};
