interface ApplicationQuestion {
  id: number;
  title?: string;
  subtitle?: string;
  require: boolean;
  direction: "vertical" | "horizontal";
  nodes: ApplicationNode[];
}

interface ApplicationNode {}

interface ApplicationNodeBase extends ApplicationNode {
  title?: string;
  subtitle?: string;
  require: boolean;
  name: string;
  errorMessages?: string;
}

interface BaseWithValues extends ApplicationNodeBase {
  value: string[];
}

interface ApplicationRadio extends BaseWithValues {
  type: "radio";
}

interface ApplicationRadioByTwoRank extends ApplicationNode {
  type: "radioByTwoRank";
  title?: string;
  subtitle?: string;
  require: boolean;
  subNodes: BaseWithValues[];
}

interface ApplicationRadioByLayer extends ApplicationNode {
  type: "radioByLayer";
  subNodes: BaseWithValues[];
}

interface ApplicationText extends ApplicationNodeBase {
  type: "text";
}

interface ApplicationTextarea extends ApplicationNodeBase {
  type: "textarea";
}

interface ApplicationBooleanTextBox extends ApplicationNodeBase {
  type: "booleanTextBox";
  subNodes: {
    type: "true" | "false";
    title?: string;
    subtitle?: string;
    require: boolean;
    name: string;
  }[];
}

interface ApplicationBar {
  type: "bar";
}

interface ApplicationJustText {
  type: "justText";
  title: string;
  subtitle?: string;
}

interface ApplicationCheckboxType extends ApplicationNodeBase {
  type: "checkbox";
  value: string[];
}

interface ApplicationCheckboxWithEtcType extends ApplicationNodeBase {
  type: "checkboxWithEtc";
  value: string[];
}
