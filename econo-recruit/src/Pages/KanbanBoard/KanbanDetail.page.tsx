import { useParams } from 'react-router'
import { useRecoilValue } from 'recoil'
import { KanbanDataArrayState } from '../../Atoms/KanbanBoard/Kanban.atoms'
import { useSearchParams } from 'react-router-dom'
import KanbanColumnComponent from '../../Components/KanbanBoard/Column.components'
import KanbanApplicationComponent from '../../Components/KanbanDetail/Application.element'
import KanbanUserElement from '../../Components/KanbanDetail/User.element'
import KanbanLabelElement from '../../Components/KanbanDetail/Label.element'

const KanbanDetailPage = () => {
  const { period } = useParams()
  const [searchParams] = useSearchParams()
  const detailId = searchParams.get('id') ?? '0'
  const detailRow = searchParams.get('row') ?? '0'

  const kanbanDataArray = useRecoilValue(KanbanDataArrayState)
  const rowTitle = kanbanDataArray[+detailRow].title
  const columnCount = kanbanDataArray[+detailRow].column.length
  const detailData = kanbanDataArray[+detailRow].column.filter((col) => col.id === +detailId)[0]

  const onBackClick = () => {
    location.href = `/kanban/${period}`
  }

  return (
    <div className="pl-24 w-screen h-screen">
      <div className="flex items-center pt-24 pb-6 text-3xl font-bold border-b-4">
        <button onClick={onBackClick} className="-translate-x-12 w-8">
          <img src="/chevron.backward.svg" alt="" />
        </button>
        <div className="-ml-8">{period}기 신입모집</div>
      </div>
      <div className="flex mt-8 overflow-auto">
        <div className="max-h-[calc(100vh-20rem)] ">
          <div className="flex gap-4">
            <div className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white">
              <div className="flex justify-between">
                <div className="flex gap-2 items-center">
                  <div className="font-bold text-lg">{rowTitle}</div>
                  <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
                    {columnCount}
                  </div>
                </div>
                <button>
                  <img src="/ellipsis.bubble.svg" alt="RowDetail" />
                </button>
              </div>
              <div className="flex flex-col justify-between">
                {kanbanDataArray[+detailRow].column.map((col) => (
                  <div className="my-2">
                    <KanbanColumnComponent data={col} row={+detailRow} />
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
        <div className="flex flex-1 pl-12">
          <div className="flex-1 min-w-[28rem]">
            <KanbanUserElement />
            <KanbanLabelElement />
          </div>
          <div className="flex-1 pr-24 min-w-[40rem]">
            <KanbanApplicationComponent />
          </div>
        </div>
      </div>
    </div>
  )
}

export default KanbanDetailPage
