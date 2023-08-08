export const CURRENT_GENERATION = 26;

export const MAIN_MENU = [
  {
    title: `${CURRENT_GENERATION} PAGE`,
    subtitle: "신입모집 신청 페이지",
    href: `/application/${CURRENT_GENERATION}`,
  },
  {
    title: "KANBAN BOARD",
    subtitle: "신입모집 칸반보드",
    href: `/kanban/${CURRENT_GENERATION}`,
  },
  {
    title: "INTERVIEW",
    subtitle: "신입모집 면접 기록",
    href: `/interview/${CURRENT_GENERATION}`,
  },
  {
    title: "APPLICANT VIEW",
    subtitle: "신입모집 지원현황",
    href: `/applicant/${CURRENT_GENERATION}`,
  },
  { title: "SHARE POINT", subtitle: "신입모집 쉐어포인트", href: "#" },
  { title: "HISTORY", subtitle: "지난 신입모집", href: "#" },
] as const;
