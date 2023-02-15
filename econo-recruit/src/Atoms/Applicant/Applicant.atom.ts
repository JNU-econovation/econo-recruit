import { atom } from 'recoil'

type applicantBoardState = {
  title: string
  apply: string[]
  semester: string
  registerDate: string
}

export const applicantListState = atom({
  key: 'applicantListState',
  default: [] as applicantBoardState[],
})
