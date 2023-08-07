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

const ApplicationBooleanTextarea = dynamic(
  () => import("./ApplicationNode/BooleanTextarea.component")
);

export const junctionQuestion = (applicationNodeData: ApplicationNode) => {
  const jsxNode = {
    radio: <ApplicationRadio data={applicationNodeData} />,
    radioByTwoRank: <ApplicationRadioByTwoRank data={applicationNodeData} />,
    radioByLayer: "",
    text: <ApplicationText data={applicationNodeData} />,
    textarea: <ApplicationTextarea data={applicationNodeData} />,
    booleanTextarea: <ApplicationBooleanTextarea data={applicationNodeData} />,
    bar: "",
    justText: "",
    checkbox: "",
    checkboxWithEtc: <ApplicationCheckboxWithEtc data={applicationNodeData} />,
  };

  return jsxNode[applicationNodeData.type];
};
