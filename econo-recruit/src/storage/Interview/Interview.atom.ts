import { atom } from 'recoil';

type interviewBoardState = {
  title: string;
  apply: string[];
  score: string;
  registerDate: string;
};

export const interviewListState = atom({
  key: 'interviewListState',
  default: [] as interviewBoardState[],
});

export const interviewPopupBooleanState = atom({
  key: 'interviewPopupBooleanState',
  default: false,
});
