import { deepFreeze } from '../../utils/objectController';

export const MAIN_MENU = [
  {
    title: '25 PAGE',
    subtitle: '신입모집 신청 페이지',
    href: '/application/25',
  },
  { title: 'KANBAN BOARD', subtitle: '신입모집 칸반보드', href: '/kanban/25' },
  { title: 'INTERVIEW', subtitle: '신입모집 면접 기록', href: '/interview/25' },
  {
    title: 'APPLICANT VIEW',
    subtitle: '신입모집 지원현황',
    href: '/applicant/25',
  },
  { title: 'SHARE POINT', subtitle: '신입모집 쉐어포인트', href: '#' },
  { title: 'HISTORY', subtitle: '지난 신입모집', href: '#' },
];

deepFreeze(MAIN_MENU);
