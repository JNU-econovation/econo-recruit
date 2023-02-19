import { deepFreeze } from '../../utils/objectController'

export const APPLICATION_REPORT_FIELD = ['APP', 'WEB', 'GAME', 'AI', 'IoT', 'AR/VR'] as const

type application_report = {
  id: number
  title: string
  subtitle: string
  value: {
    type?: string
    title?: string
    value?: any[]
    absolete?: boolean
    subtitle?: string
    example?: string
  }[]
}

export const APPLICATION_REPORT = [
  {
    id: 1,
    title: '프로젝트 희망 분야를 선택해주세요.',
    subtitle: '1순위, 2순위 해당 분야를 모두 선택해주세요.',
    value: [
      {
        type: 'button',
        title: 'area',
        value: ['개발자', '기획자', '디자이너'],
        absolute: true,
      },
      {
        type: 'button',
        title: 'field',
        value: APPLICATION_REPORT_FIELD,
        absolute: true,
      },
    ],
  },
  {
    id: 2,
    title: '기본 인적 사항을 입력해주세요.',
    value: [
      {
        type: 'text',
        title: '이름',
        absolute: true,
      },
      {
        type: 'text',
        title: '연락처',
        absolute: true,
      },
      {
        type: 'text',
        title: '학번',
        absolute: true,
        example: '(ex. 123456)',
      },
      {
        id: 3,
        title: '학년 및 학기',
        value: ['1학년', '2학년', '3학년', '4학년'],
        value2: ['1학기', '2학기'],
        absolute: true,
      },
    ],
  },
  {
    id: 3,
    title: '기본 인적사항을 입력해주세요.',
    subtitle: '복수전공과 부전공은 선택 입력사항입니다.',
    value: [
      {
        type: 'text',
        title: '전공',
        absolute: true,
      },
      {
        type: 'text',
        title: '복수전공',
        absolute: false,
      },
      {
        type: 'text',
        title: '부전공',
        absolute: false,
      },
    ],
  },
  {
    id: 4,
    title: '기타 질문 사항에 답변해주세요.',
    value: [
      {
        type: 'text',
        title: '학업 외에 병행하고 있거나 향후 계획 중에 있는 활동이 있으시다면 적어주세요.',
        subtitle: '(동아리, 연구실, 아르바이트, 스터디, 교환학생 등)',
        absolute: false,
      },
      {
        type: 'text',
        title: '지원 경로',
        subtitle: '(중복 선택 가능)',
        absolute: true,
        value: [
          '학과 공지사항',
          '홍보 포스터',
          '지인 소개',
          '인스타그램',
          '페이스북',
          '에브리타임',
        ],
        example: '중복 선택 가능',
      },
    ],
  },
  {
    id: 5,
    title: '자신을 어필할 수 있는 포트폴리오를 업로드해주세요.',
    subtitle: '포트폴리오 용량이 1GB가 넘어간다면 메일로 제출해주세요.',
    value: [
      {
        type: 'text',
        title: '참고 URL',
        absolute: false,
        example: '(Github, Blog, Notion, Website 등)',
      },
      {
        type: 'text',
        title: '파일 업로드',
        absolute: false,
        example: '(Google Drive 등)',
      },
      {
        type: 'text',
        title:
          '기획자를 지원하는 경우 이번 학기에 진행하고 싶은 프로젝트의 기획서를 제출해 주세요.',
        absolete: false,
        example: '단, 제출한 기획을 기반으로 이번 학기에 프로젝트를 진행하지 못할 수 있습니다.',
        value: [
          {
            type: 'text',
            title: '파일 업로드',
            absolute: false,
            example: '(Google Drive 등)',
          },
        ],
      },
    ],
  },
  {
    id: 6,
    title: '합격 여부 전달을 위하여 이메일을 입력해주세요.',
    subtitle: '이메일을 기재하지 않을 시, 합격이 취소될 수 있습니다.',
    value: [
      {
        type: 'text',
        title: 'E-mail',
        absolute: true,
      },
    ],
  },
  {
    id: 7,
    title:
      "에코노베이션은 3학기 이상의 활동을 권장하고 있으며 매주 금요일 17시에는 주간발표가 있습니다.\n위 내용을 확인하셨으면 '확인했습니다'를 기입해주세요.",
    value: [
      {
        type: 'text',
        title: '',
        absolute: false,
      },
    ],
  },
  {
    id: 8,
    title: '개인정보 수집에 관한 안내 및 개인정보 수집에 대한 안내에 동의하시나요?',
    value: [
      {
        type: 'checkbox',
        title: '개인정보 수집(공통)에 대한 안내',
        absolute: false,
      },
      {
        type: 'checkbox',
        title: '개인정보 수집(포트폴리오)에 대한 안내',
        absolute: false,
      },
    ],
  },
] as application_report[]

deepFreeze(APPLICATION_REPORT)

export const APPLICATION_QUESTION = [
  { title: '에코노베이션에 지원하게 된 계기는 무엇인가요?' },
  {
    title:
      'IT 분야에서 앞으로 도전해 보고 싶은 기획이나 기술 혹은 분야가 있다면 그 이유와 함께 서술해 주세요.',
  },
  {
    title:
      '소프트웨어 프로젝트 관련 개발/기획/디자인 경험이나 주도적으로 교육에 참여한 경험이 있나요?',
    children: [
      { type: true, title: '본인이 어떤 역활을 했고, 경험을 통해 무엇을 얻었는지 적어주세요.' },
    ],
  },
  { title: 'ECONOVATION에 들어오게 된다면 어떤 목표와 학습 계획을 바탕으로 활동하고 싶나요?' },
  {
    title:
      '무언가에 깊게 빠지거나 파고 들어본 적이 있나요? 좋아하는 것을 위해서 주변에서 인정할 정도로 깊게 빠져본 적이 있다면 서술해주세요.',
  },
  {
    title:
      '협업(프로젝트, 팀 활동)에 있어서 가장 중요하다고 생각되는 것은 무엇인지 그 이유와 함게 서술해주세요.',
  },
]

deepFreeze(APPLICATION_QUESTION)

export const APPLICATION_CAN_TIME = {
  title: '면접 가능시간을 선택해주세요.(중복 선택 가능)',
}

deepFreeze(APPLICATION_CAN_TIME)
