import { useParams } from 'react-router'

const KanbanBoardPage = () => {
  const { period } = useParams()
  return (
    <div className="pl-24">
      <div className="pt-24 pb-6 text-3xl font-bold border-b-4">{period}기 신입모집</div>
    </div>
  )
}

export default KanbanBoardPage
