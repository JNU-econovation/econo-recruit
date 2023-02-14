export const ApplicationQuestion = [
  {
    title: '프로젝트 희망 분야를 선택해주세요.*',
    absolute: true,
  },
  {
    title: '기본 인적 사항을 입력해주세요.*',
    absolute: true,
  },
  {
    title: '기타 질문 사항에 답변해주세요.',
    absolute: true,
    children: [
      {
        title: '향후 계획 활동',
        absolute: false,
      },
      {
        title: '지원경로* (중복 선택 가능)',
        absolute: true,
      },
    ],
  },
  {
    title: '에코노베이션에 지원하게 된 계기는 무엇인가요?',
    absolute: false,
  },
  {
    title:
      'IT 분야에서 앞으로 도전해 보고 싶은 기획이나 기술 혹은 분야가 있다면 그 이유와 함께 서술해 주세요.',
    absolute: false,
  },
  {
    title:
      '소프트웨어 프로젝트 관련 개발/기획/디자인 경험이나 주도적으로 교육에 참여한 경험이 있나요?',
    absolute: false,
    children: [
      {
        type: true,
        title: '본인이 어떤 역활을 했고, 경험을 통해 무엇을 얻었는지 적어주세요.',
        absolute: false,
      },
    ],
  },
  {
    title: 'ECONOVATION에 들어오게 된다면 어떤 목표와 학습 계획을 바탕으로 활동하고 싶나요?',
    absolute: false,
  },
  {
    title:
      '무언가에 깊게 빠지거나 파고 들어본 적이 있나요? 좋아하는 것을 위해서 주변에서 인정할 정도로 깊게 빠져본 적이 있다면 서술해주세요.',
    absolute: false,
  },
  {
    title:
      '협업(프로젝트, 팀 활동)에 있어서 가장 중요하다고 생각되는 것은 무엇인지 그 이유와 함게 서술해주세요.',
    absolute: false,
  },
  {
    title:
      "에코노베이션은 3학기 이상의 활동을 권장하고 있으며 매주 금요일 17시에는 주간발표가 있습니다. 위 내용을 확인하셨으면 '확인했습니다'를 기입해주세요.*",
    absolute: true,
  },
  {
    title: '면접 가능시간을 선택해주세요.(중복 선택 가능)',
    absolute: true,
  },
]
