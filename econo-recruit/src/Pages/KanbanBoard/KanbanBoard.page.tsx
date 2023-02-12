import { useParams } from 'react-router'
import KanbanNavbarComponent from '../../Components/KanbanBoard/Navbar.components'

const KanbanBoardPage = () => {
  const { period } = useParams()
  return (
    <div className="pl-24">
      <div className="pt-24 pb-6 text-3xl font-bold border-b-4">{period}기 신입모집</div>
      <div className="flex gap-4 py-6 font-medium">
        <KanbanNavbarComponent />
      </div>
    </div>
  )
}

export default KanbanBoardPage
