import { atom } from 'recoil'
import { KanbanMock } from '../../mock/MockData'

export type KanbanRowData = {
  id: number
  title: string
  column: KanbanColumnData[]
}

export type KanbanColumnData = {
  id: number
  major: string
  title: string
  apply: string[]
  comment: number
  heart: number
  isHearted: boolean
}

export const KanbanDataArrayState = atom({
  key: 'KanbanDataArrayState',
  default: KanbanMock as KanbanRowData[],
})
