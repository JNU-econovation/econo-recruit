import { atom } from 'recoil'

export const ApplicationNavbarIndexState = atom({
  key: 'ApplicationNavbarIndexState',
  default: 0,
})

type ApplicationResult = {
  type: string
  field1: string
  field2: string
}

export const ApplicationResultDataState = atom({
  key: 'ApplicationResultDataState',
  default: {} as ApplicationResult,
})
