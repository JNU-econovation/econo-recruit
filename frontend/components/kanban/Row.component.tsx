"use client";

import { Draggable, Droppable } from "@hello-pangea/dnd";
import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import KanbanAddCardComponent from "./AddCard.component";
import KanbanCardComponent from "./Card.component";
import { useState } from "react";
import KanbanRowDetailComponent from "./RowDetail.compontent";

type KanbanRowComponent = {
  index: number;
  title: string;
  CardCount: number;
  CardData: KanbanCardData[];
};

const KanbanRowComponent = ({
  index,
  CardData,
  title,
  CardCount,
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
                {CardCount}
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
                  {CardData.map((Card, colIndex) => (
                    <Draggable
                      draggableId={`${colIndex}-${Card.id}`}
                      index={colIndex}
                      key={Card.id}
                    >
                      {(provided) => (
                        <div
                          ref={provided.innerRef}
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                          className="my-4"
                        >
                          <KanbanCardComponent data={Card} row={index} />
                        </div>
                      )}
                    </Draggable>
                  ))}
                  {provided.placeholder}
                </div>
              )}
            </Droppable>
          </div>
          <KanbanAddCardComponent AddCardCallBack={() => ""} />
        </div>
      )}
    </Draggable>
  );
};

export default KanbanRowComponent;
