import { KanbanColumnData } from '../../Atoms/KanbanBoard/Kanban.atoms'

type KanbanColumnComponent = {
  data: KanbanColumnData
}

const KanbanColumnComponent = ({ data }: KanbanColumnComponent) => {
  const { major, title, apply, comment, isHearted, heart } = data

  return (
    <div className="border-[1px] border-[#F0F0F0] w-full p-3 rounded-lg drop-shadow-md bg-white hover:border-[#7AA0FF]">
      <div className="text-xs text-[#666666]">{major}</div>
      <div className="font-bold">{title}</div>
      <div className="mt-2 flex justify-between items-center text-sm text-[#666666]">
        <div className="text-sm">{apply.join(' / ')}</div>
        <div className="flex gap-3">
          <div className="flex">
            <img src="/bubble.right.svg" alt="comment" />
            {comment}
          </div>
          <div className="flex">
            {isHearted ? (
              <img src="/heart.point.svg" alt="heart" />
            ) : (
              <img src="/heart.svg" alt="heart" />
            )}
            {heart}
          </div>
        </div>
      </div>
    </div>
  )
}

export default KanbanColumnComponent
