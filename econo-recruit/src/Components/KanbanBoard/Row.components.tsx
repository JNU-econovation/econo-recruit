import { Draggable, Droppable } from '@hello-pangea/dnd'
import KanbanAddColumnComponent from './AddColumn.component'
import KanbanColumnComponent from './Column.components'
import { KanbanColumnData } from '../../Atoms/KanbanBoard/Kanban.atoms'

type KanbanRowComponent = {
  index: number
  title: string
  columnCount: number
  columnData: KanbanColumnData[]
}

const KanbanRowComponent = ({ index, columnData, title, columnCount }: KanbanRowComponent) => {
  return (
    <Draggable draggableId={`${index}`} index={index}>
      {(provided) => (
        <div
          className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white"
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
        >
          <div className="flex justify-between">
            <div className="flex gap-2 items-center">
              <div className="font-bold text-lg">{title}</div>
              <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
                {columnCount}
              </div>
            </div>
            <button>
              <img src="/ellipsis.bubble.svg" alt="RowDetail" />
            </button>
          </div>
          <div className="flex flex-col justify-between overflow-auto max-h-[calc(100vh-24rem)]">
            <Droppable droppableId={`${index}`} key={index}>
              {(provided) => (
                <div ref={provided.innerRef} {...provided.droppableProps}>
                  <Draggable draggableId={`${index}-${-1}`} index={-1} key={-1}>
                    {(provided) => (
                      <div
                        ref={provided.innerRef}
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}
                        className="py-1"
                      ></div>
                    )}
                  </Draggable>
                  {columnData.map((column, index) => (
                    <Draggable draggableId={`${index}-${column.id}`} index={index} key={column.id}>
                      {(provided) => (
                        <div
                          ref={provided.innerRef}
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                          className=" my-4"
                        >
                          <KanbanColumnComponent data={column} />
                        </div>
                      )}
                    </Draggable>
                  ))}
                  {provided.placeholder}
                </div>
              )}
            </Droppable>
          </div>
          <KanbanAddColumnComponent AddColumnCallBack={() => ''} />
        </div>
      )}
    </Draggable>
  )
}

export default KanbanRowComponent
