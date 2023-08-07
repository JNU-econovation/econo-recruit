import { ReplacerType } from "@/functions/replacer";
import { ValidatorType } from "@/functions/validator";

export interface ApplicationQuestion {
  id: number;
  title?: string;
  subtitle?: string;
  require: boolean;
  direction: "vertical" | "horizontal" | "booleanTextarea";
  nodes: ApplicationNode[];
}

export type ApplicationNodeTypes =
  | "radio"
  | "radioByTwoRank"
  | "text"
  | "textarea"
  | "booleanTextarea"
  | "bar"
  | "justText"
  | "checkbox"
  | "checkboxWithEtc";

export interface ApplicationNode {
  type: ApplicationNodeTypes;
}

export interface ApplicationNodeBase extends ApplicationNode {
  title?: string;
  subtitle?: string;
  require: boolean;
  name: string;
  errorMessages?: string;
}

export interface BaseWithValues extends ApplicationNodeBase {
  value: string[];
}

export interface ApplicationRadio extends BaseWithValues {
  type: "radio";
}

export interface BaseWithValuesWithSplitNumber extends BaseWithValues {
  splitNumber: number;
}

export interface ApplicationRadioByTwoRank extends ApplicationNode {
  type: "radioByTwoRank";
  title?: string;
  subtitle?: string;
  require: boolean;
  subNodes: BaseWithValuesWithSplitNumber[];
}

export interface ApplicationText extends ApplicationNodeBase {
  type: "text";
  validate?: ValidatorType;
  replace?: ReplacerType;
  maxLength?: number;
  minLength?: number;
}

export interface ApplicationTextarea extends ApplicationNodeBase {
  type: "textarea";
}

export interface ApplicationBooleanTextarea extends ApplicationNodeBase {
  type: "booleanTextarea";
  value: string[];
  subNodes: {
    type: "true" | "false";
    title?: string;
    subtitle?: string;
    require: boolean;
    name: string;
  }[];
}

export interface ApplicationBar extends ApplicationNodeBase {
  type: "bar";
}

export interface ApplicationJustText extends ApplicationNodeBase {
  type: "justText";
  title: string;
  subtitle?: string;
}

export interface ApplicationCheckboxType extends ApplicationNodeBase {
  type: "checkbox";
  value: string[];
}

export interface ApplicationCheckboxWithEtcType extends ApplicationNodeBase {
  type: "checkboxWithEtc";
  value: string[];
}
