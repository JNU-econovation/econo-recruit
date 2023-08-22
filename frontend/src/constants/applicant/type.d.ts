type ApplicantNodeType =
  | "customField"
  | "customHuman"
  | "shortSplit"
  | "textarea"
  | "booleanTextarea"
  | "timeline";

interface ApplicantNode {
  type: ApplicantNodeType;
  title: string;
  id: number;
}

interface ApplicantValue {
  name: string;
  title?: string;
}

interface ApplicantCustomFieldNode extends ApplicantNode {
  type: "customField";
  value: ApplicantValue;
  subValue: ApplicantValue[];
}

interface ApplicantCustomHumanNode extends ApplicantNode {
  type: "customHuman";
  value: {
    hunamName: ApplicantValue;
    humanPhone: ApplicantValue;
    humanEmail: ApplicantValue;
    humanEtc: ApplicantValue[];
  };
}

interface ApplicantShortSplitNode extends ApplicantNode {
  type: "shortSplit";
  value: ApplicantValue[];
}

interface ApplicantTextareaNode extends ApplicantNode {
  type: "textarea";
  value: ApplicantValue;
}

interface ApplicantBooleanTextareaNode extends ApplicantNode {
  type: "booleanTextarea";
  subtitle: string[];
  booleanValue: ApplicantValue;
  value: ApplicantValue;
}

interface ApplicantTimelineNode extends ApplicantNode {
  type: "timeline";
  name: string;
}
