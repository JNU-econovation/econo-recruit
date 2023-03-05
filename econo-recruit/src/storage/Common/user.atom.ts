import { atom } from 'recoil';

export const USER_AUTHORITY = ['chairman', 'manager', 'TF'] as const;
export type user_authority = (typeof USER_AUTHORITY)[number];

type userInformation = {
  name: string;
  period: number;
  authority: user_authority;
};

export const userInformationState = atom({
  key: 'userInformationState',
  default: {
    name: '임채승',
    period: 22,
    authority: 'chairman',
  } as userInformation,
});
