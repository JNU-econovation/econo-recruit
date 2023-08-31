import { Draggable, Droppable } from "@hello-pangea/dnd";
import KanbanCardComponent from "./Card.component";
import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import KanbanAddCardComponent from "./AddCard.component";

type KanbanColumnComponent = {
  index: number;
  title: string;
  columnCount: number;
  columnData: (KanbanCardData | null)[];
};

const KanbanColumnComponent = ({
  index,
  columnData,
  title,
  columnCount,
}: KanbanColumnComponent) => {
  return (
    <Draggable draggableId={`${index}`} index={index} key={`column-${index}`}>
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
              <img src="/icons/ellipsis.bubble.svg" alt="ColumnDetail" />
            </button>
          </div>
          <div className="flex flex-col justify-between overflow-auto max-h-[calc(100vh-24rem)]">
            <Droppable droppableId={`${index}`} key={index}>
              {(provided) => (
                <div
                  key={index}
                  ref={provided.innerRef}
                  {...provided.droppableProps}
                >
                  {columnData.map((column, colIndex) =>
                    column?.cardType === "INVISIBLEaa" ? (
                      <Draggable
                        draggableId={`${index}-${-1}`}
                        index={-1}
                        key={`column-${index}`}
                      >
                        {(provided) => (
                          <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            className="py-1"
                          ></div>
                        )}
                      </Draggable>
                    ) : (
                      <Draggable
                        draggableId={`${colIndex}-${column?.id}`}
                        index={colIndex}
                        key={column?.id}
                      >
                        {(provided) => (
                          <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            className="my-4"
                          >
                            <KanbanCardComponent data={column} cardId={index} />
                          </div>
                        )}
                      </Draggable>
                    )
                  )}
                  {provided.placeholder}
                </div>
              )}
            </Droppable>
          </div>
          <KanbanAddCardComponent AddCardCallBack={() => ""} columnId={index} />
        </div>
      )}
    </Draggable>
  );
};

export default KanbanColumnComponent;
