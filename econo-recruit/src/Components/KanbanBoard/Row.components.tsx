import KanbanAddColumnComponent from './AddColumn.component'
import KanbanColumnComponent from './Column.components'

const KanbanRowComponent = () => {
  const { title, columnCount } = { columnCount: 24, title: '[개발자] 지원서' }
  const ColumnData = [
    {
      major: '소프트웨어공학과',
      title: '[개발자] 임채승',
      apply: ['APP', 'WEB'],
      comment: 14,
      heart: 6,
      isHearted: true,
    },
  ]

  return (
    <div className="max-h-full border-[1px] w-fit p-4 rounded-lg min-w-[17rem]">
      <div className="flex justify-between">
        <div className="flex gap-2">
          <div className="font-bold text-lg">{title}</div>
          <div className="flex justify-center items-center px-2 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF]">
            {columnCount}
          </div>
        </div>
        <button>
          <img src="/public/ellipsis.bubble.svg" alt="RowDetail" />
        </button>
      </div>
      {ColumnData.map((column) => (
        <KanbanColumnComponent data={column} />
      ))}
      <KanbanAddColumnComponent AddColumnCallBack={() => ''} />
    </div>
  )
}

export default KanbanRowComponent
