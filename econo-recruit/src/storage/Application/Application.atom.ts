import { atom } from 'recoil'

export const ApplicationNavbarIndexState = atom({
  key: 'ApplicationNavbarIndexState',
  default: 0,
})

type ApplicationResult = {
  type: string // 지원분야
  field1: string // 지원목록1
  field2: string // 지원목록2
  name: string // 이름
  cellphone: string // 전화번호
  undergrad: string // 학번
  grade: string // 학년
  semester: string //학기
}

export const ApplicationResultDataState = atom({
  key: 'ApplicationResultDataState',
  default: {} as ApplicationResult,
})
