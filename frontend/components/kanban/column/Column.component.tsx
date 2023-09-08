import { Draggable, Droppable } from "@hello-pangea/dnd";
import KanbanCardComponent from "../card/Card.component";
import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import KanbanAddCardComponent from "../card/AddCard.component";
import { FC } from "react";
import { KanbanCard } from "../card";

interface KanbanColumnComponentProps {
  columnIndex: number;
  title: string;
  columnCount: number;
  columnData: (KanbanCardData | null)[];
  columnId: number;
}

interface KanbanColumnProps {
  columnIndex: number;
  columnData: (KanbanCardData | null)[];
}

const KanbanColumnDroppable: FC<KanbanColumnProps> = ({
  columnIndex,
  columnData,
}) => {
  return (
    <Droppable droppableId={`${columnIndex}`} key={columnIndex}>
      {(provided) => (
        <div
          key={columnIndex}
          ref={provided.innerRef}
          {...provided.droppableProps}
        >
          {columnData.map((column, index) => {
            switch (column?.cardType) {
              case "INVISIBLE":
                return (
                  <KanbanCard.Invisible
                    key={column?.id}
                    index={index}
                    columnIndex={columnIndex}
                  />
                );
              case "APPLICANT":
                return (
                  <KanbanCard.Applicant
                    key={column?.id}
                    column={column}
                    columnIndex={columnIndex}
                    index={index}
                  />
                );
              default:
                return <></>;
            }
          })}
          {provided.placeholder}
        </div>
      )}
    </Droppable>
  );
};

const KanbanColumnComponent: FC<KanbanColumnComponentProps> = ({
  columnIndex,
  columnData,
  title,
  columnCount,
  columnId,
}) => {
  return (
    <Draggable
      draggableId={`${columnIndex}`}
      index={columnIndex}
      key={`column-${columnIndex}`}
    >
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
            <KanbanColumnDroppable
              columnData={columnData}
              columnIndex={columnIndex}
            />
          </div>
          <KanbanAddCardComponent columnId={columnId} />
        </div>
      )}
    </Draggable>
  );
};

export default KanbanColumnComponent;
