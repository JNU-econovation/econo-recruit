import { deepFreeze } from '@/utils/objectController';

export const MainNavbar = [
  {
    title: '신입모집 신청 페이지',
    short_title: '신청',
    type: 'apply',
    target: '_blank',
    href: '/application/25',
  },
  {
    title: '신업모집 칸반보드',
    short_title: '칸반보드',
    type: 'kanban',
    target: 'none',
    href: '/kanban/25',
  },
  {
    title: '신입모집 면접 기록',
    short_title: '면접기록',
    type: 'interview',
    target: 'none',
    href: '/interview/25',
  },
  {
    title: '신입모집 지원현황',
    short_title: '지원현황',
    type: 'applicant',
    target: 'none',
    href: '/applicant/25',
  },
  {
    title: '신입모집 쉐어 포인트',
    short_title: '쉐어포인트',
    type: 'sharepoint',
    target: '_blank',
    href: '#',
  },
  {
    title: '지난 신입모집',
    short_title: '자난 모집',
    type: 'history',
    target: '_blank',
    href: '#',
  },
];

deepFreeze(MainNavbar);
