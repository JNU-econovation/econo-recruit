export const APPLICANT_MANAGER = [
  {
    id: 5,
    title: "자기소개 및 에코노베이션에 지원하게 된 계기를 서술해 주세요.",
    type: "textarea",
    value: { name: "reason" },
  } as ApplicantTextareaNode,
  {
    id: 6,
    title:
      "본인이 계획하고 있는 진로가 무엇인가요? (IT 분야가 아니어도 괜찮습니다.)",
    type: "textarea",
    value: { name: "future" },
  } as ApplicantTextareaNode,
  {
    id: 7,
    title:
      "PM(기획자)은 아이디어를 낼 뿐만 아니라, 자신의 아이디어를 동료들이 이해할 수 있는 형식으로 효과적으로 전달해야 하는 역할입니다. 지원자님께서 자신의 아이디어를 시각적으로 구현하여, 구체적으로 전달한 경험이 있다면 서술해 주세요. 어떤 아이디어를 어떤 형태로 구현하여, 누구에게 전달했는지 잘 드러나도록 작성해 주세요.",
    type: "textarea",
    value: { name: "future" },
  } as ApplicantTextareaNode,
  {
    id: 8,
    title:
      "지원자님께서 직접 IT 서비스(UI/UX)를 설계하거나 개선한 경험이 있나요?(캡스톤 디자인 제외)",
    type: "booleanTextarea",
    booleanValue: { name: "experience" },
    subtitle: [
      "본인이 어떤 역할을 했고, 그 경험을 통해 무엇을 얻었는지 서술해 주세요.",
      "우리 주변의 IT서비스 중에 개선하고 싶은 서비스가 있다면, 어떤 점을 '왜' 그리고 '어떻게' 개선하고 싶은지 서술해 주세요.",
    ],
    value: { name: "experienceTextarea" },
  } as ApplicantBooleanTextareaNode,
  {
    id: 8,
    title:
      "어떤 일에 도전하고 실패해 본 경험이 있나요? 그 실패를 어떻게 극복했는지 서술해 주세요. (소프트웨어 분야 관련 경험이 아니어도 좋습니다.)",
    type: "textarea",
    value: { name: "failual" },
  } as ApplicantTextareaNode,
  {
    id: 9,
    title:
      "무언가에 깊게 빠지거나 파고 들어본 적이 있나요? 좋아하는 것을 위해서 주변에서 인정할 정도로 깊게  빠져본 적이 있다면 서술해주세요. (소프트웨어 분야 관련 경험이 아니어도 좋습니다.)",
    type: "textarea",
    value: { name: "deep" },
  } as ApplicantTextareaNode,
  {
    id: 10,
    title:
      "협업(프로젝트, 팀 활동)에 있어서 가장 중요하다고 생각되는 것은 무엇인지 그 이유와 함께 지원자님의 생각을 서술해 주세요.",
    type: "textarea",
    value: { name: "collaboration" },
  } as ApplicantTextareaNode,
  {
    id: 11,
    title:
      "에코노베이션에 최종 합격 시 신입 기수로 구성된 팀으로 개발 프로젝트에 참여하고, 목표를 달성하기 위해 스스로 끊임없이 배우고 노력합니다. 에코노베이션에 들어오게 된다면 어떤 목표와 학습 계획을 바탕으로 활동하고 싶나요?",
    type: "textarea",
    value: { name: "studyPlan" },
  } as ApplicantTextareaNode,
  {
    id: 12,
    title: "면접 가능시간을 선택해주세요. (중복 선택 가능)",
    type: "timeline",
  } as ApplicantTimelineNode,
];