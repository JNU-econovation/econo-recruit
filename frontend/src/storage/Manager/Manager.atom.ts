import { atom } from 'jotai';

type managerBoardState = {
  name: string;
  period: number;
  type: string;
};

export const managerListState = atom<managerBoardState[]>([]);

export const managerPopupBooleanState = atom( false );
