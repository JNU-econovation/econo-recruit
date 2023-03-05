import { atom } from 'recoil';

type managerBoardState = {
  name: string;
  period: number;
  type: string;
};

export const managerListState = atom({
  key: 'managerListState',
  default: [] as managerBoardState[],
});

export const managerPopupBooleanState = atom({
  key: 'managerPopupBooleanState',
  default: false,
});
