import { atom } from 'jotai';

export const ApplicationNavbarIndexState = atom(0);

type ApplicationResult = {
  type: string; // 지원분야
  field1: string; // 지원목록1
  field2: string; // 지원목록2
  name: string; // 이름
  cellphone: string; // 전화번호
  undergrad: string; // 학번
  grade: string; // 학년
  semester: string; //학기
  major: string; // 주전공
  doubleMajor: string; // 복수 전공
  minor: string; // 부 전공
  working: string; // 하고 있는 일
  supportPath: string; // 지원 경로
  supportPathAddtional: string; // 지원 경로 추가
  question: string[];
};
