"use client";

import { Draggable, Droppable } from "@hello-pangea/dnd";
import { KanbanColumnData } from "@/src/stores/kanban/Kanban.atoms";
import KanbanAddColumnComponent from "./AddColumn.component";
import KanbanColumnComponent from "./Column.component";
import { useState } from "react";
import KanbanRowDetailComponent from "./RowDetail.compontent";

type KanbanRowComponent = {
  index: number;
  title: string;
  columnCount: number;
  columnData: KanbanColumnData[];
};

const KanbanRowComponent = ({
  index,
  columnData,
  title,
  columnCount,
}: KanbanRowComponent) => {
  const [isDetailOpen, setIsDetailOpen] = useState(false);
  return (
    <Draggable draggableId={`${index}`} index={index}>
      {(provided) => (
        <div
          className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white"
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
        >
          <div className="flex justify-between relative">
            <div className="flex gap-2 items-center">
              <div className="font-bold text-lg">{title}</div>
              <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
                {columnCount}
              </div>
            </div>
            <button type="button" onClick={() => setIsDetailOpen(true)}>
              <img src="/icons/ellipsis.bubble.svg" alt="RowDetail" />
            </button>
            {isDetailOpen ? (
              <KanbanRowDetailComponent setIsDetailOpen={setIsDetailOpen} />
            ) : (
              ""
            )}
          </div>
          <div className="flex flex-col justify-between overflow-auto h-fit">
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
                      />
                    )}
                  </Draggable>
                  {columnData.map((column, colIndex) => (
                    <Draggable
                      draggableId={`${colIndex}-${column.id}`}
                      index={colIndex}
                      key={column.id}
                    >
                      {(provided) => (
                        <div
                          ref={provided.innerRef}
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                          className="my-4"
                        >
                          <KanbanColumnComponent data={column} row={index} />
                        </div>
                      )}
                    </Draggable>
                  ))}
                  {provided.placeholder}
                </div>
              )}
            </Droppable>
          </div>
          <KanbanAddColumnComponent AddColumnCallBack={() => ""} />
        </div>
      )}
    </Draggable>
  );
};

export default KanbanRowComponent;
