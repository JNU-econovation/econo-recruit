export const APPLICANT = [
  {
    title: "1. 프로젝트 희망 분야를 선택해주세요.*",
    type: "customField",
    value: {
      name: "field",
    },
    subValue: [
      { title: "1순위", name: "field1" },
      { title: "2순위", name: "field2" },
    ],
  } as ApplicantCustomFieldNode,
  {
    title: "2. 기본 인적 사항을 입력해주세요.*",
    type: "customHuman",
    value: {
      hunamName: { name: "name" },
      humanEmail: { name: "" },
      humanPhone: { name: "contacted" },
      humanEtc: [
        { name: "classOf" },
        { name: "major" },
        { name: "doubleMajor" },
        { name: "minor" },
        { name: "grade" },
      ],
    },
  } as ApplicantCustomHumanNode,
  {
    title: "3. 기타 질문 사항에 답변해주세요.",
    type: "shortSplit",
    value: [
      { title: "향후 계획 활동", name: "activity" },
      { title: "지원 경로* (중복 선택 가능)", name: "channel" },
    ],
  } as ApplicantShortSplitNode,
  {
    title: "4. 에코노베이션에 지원하게 된 계기는 무엇인가요?",
    type: "textarea",
    value: { name: "reason" },
  } as ApplicantTextareaNode,
  {
    title:
      "5. IT 분야에서 앞으로 도전해 보고 싶은 기획이나 기술 혹은 분야가 있다면 그 이유와 함께 서술해 주세요.",
    type: "textarea",
    value: { name: "future" },
  } as ApplicantTextareaNode,
  {
    title:
      "6. 소프트웨어 프로젝트 관련 개발/기획/디자인 경험이나 주도적으로 교육에 참여한 경험이 있나요?",
    subtitle: [
      "6-1. 본인이 어떤 역할을 했고, 경험을 통해 무엇을 얻었는지 적어주세요.",
      "6-1. 본인이 어떤 역할을 했고, 경험을 통해 무엇을 얻었는지 적어주세요.",
    ],
    type: "booleanTextarea",
    booleanValue: { name: "experience" },
    value: { name: "experienceTextarea" },
  } as ApplicantBooleanTextareaNode,
  {
    title:
      "7.  ECONOVATION에 들어오게 된다면 어떤 목표와 학습 계획을 바탕으로 활동하고 싶나요?",
    type: "textarea",
    value: { name: "goal" },
  } as ApplicantTextareaNode,
  {
    title:
      "8. 무언가에 깊게 빠지거나 파고 들어본 적이 있나요? 좋아하는 것을 위해서 주변에서 인정할 정도로 깊게 빠져본 적이 있다면 서술해주세요.",
    type: "textarea",
    value: { name: "deep" },
  } as ApplicantTextareaNode,
  {
    title:
      "9. 협업(프로젝트, 팀 활동)에 있어서 가장 중요하다고 생각되는 것은 무엇인지 그 이유와 함께 서술해주세요.",
    type: "textarea",
    value: { name: "collaboration" },
  } as ApplicantTextareaNode,
  {
    title: "14. 면접 가능시간을 선택해주세요. (중복 선택 가능)",
    type: "timeline",
  } as ApplicantTimelineNode,
] as ApplicantNode[];
