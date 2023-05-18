import { atom } from 'jotai'
import { KanbanMock } from '@/mock/MockData'

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

export const KanbanDataArrayState = atom(KanbanMock as KanbanRowData[])
