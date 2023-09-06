const APPLICANT = [
  {
    id: 1,
    title: "프로젝트 희망 분야를 선택해주세요.*",
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
    id: 2,
    title: "기본 인적 사항을 입력해주세요.*",
    type: "customHuman",
    value: {
      hunamName: { name: "name" },
      humanEmail: { name: "email" },
      humanPhone: { name: "contacted" },
      humanEtc: [
        { name: "classOf" },
        { name: "major" },
        { name: "doubleMajor" },
        { name: "minor" },
        { name: "grade" },
        { name: "semester" },
      ],
    },
  } as ApplicantCustomHumanNode,
  {
    id: 3,
    title: "기타 질문 사항에 답변해주세요.",
    type: "shortSplit",
    value: [
      { title: "향후 계획 활동", name: "activity" },
      { title: "지원 경로* (중복 선택 가능)", name: "channel" },
    ],
  } as ApplicantShortSplitNode,
] as ApplicantNode[];

const INTERVIEW_SCORE_KEYWOARD = [
  { title: "열정", name: "passion" },
  { title: "실천력", name: "energy" },
  { title: "협업", name: "collaboration" },
  { title: "동아리 집중", name: "clubFocus" },
  { title: "간절함", name: "earnestness" },
];

export { INTERVIEW_SCORE_KEYWOARD, APPLICANT };
