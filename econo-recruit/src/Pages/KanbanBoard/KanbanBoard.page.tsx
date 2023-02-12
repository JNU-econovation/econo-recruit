import { useParams } from 'react-router'
import KanbanNavbarComponent from '../../Components/KanbanBoard/Navbar.components'
import KanbanRowComponent from '../../Components/KanbanBoard/Row.components'
import KanbanAddRowComponent from '../../Components/KanbanBoard/AddRow.component'

const KanbanBoardPage = () => {
  const { period } = useParams()
  const RowData = ['a', 'b']
  return (
    <div className="pl-24 w-screen h-screen">
      <div className="pt-24 pb-6 text-3xl font-bold border-b-4">{period}기 신입모집</div>
      <div className="flex gap-4 py-6 font-medium">
        <KanbanNavbarComponent />
      </div>
      <div className="flex gap-4">
        {RowData.map((row) => (
          <KanbanRowComponent />
        ))}
        <KanbanAddRowComponent AddRowCallBack={() => ''} />
      </div>
    </div>
  )
}

export default KanbanBoardPage
