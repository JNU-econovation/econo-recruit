"use client";

import { DragDropContext, DropResult, Droppable } from "@hello-pangea/dnd";
import { useAtom } from "jotai";
import { KanbanDataArrayState } from "@/src/stores/kanban/Kanban.atoms";
import { getMovedKanbanData } from "@/src/functions/kanban";
import KanbanRowComponent from "./Card.component";
import KanbanAddRowComponent from "./Addcard.component";

const KanbanBoardDragDropComponent = () => {
  const [kanbanData, setKanbanData] = useAtom(KanbanDataArrayState);
  const onDragEnd = (result: DropResult) => {
    const movedKanbanData = getMovedKanbanData(kanbanData, result);
    setKanbanData(movedKanbanData);
  };

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Droppable droppableId="droppable" type="card" direction="horizontal">
        {(provided) => (
          <div
            className="flex gap-4"
            ref={provided.innerRef}
            {...provided.droppableProps}
          >
            {kanbanData.map((row, index) => (
              <KanbanRowComponent
                index={index}
                cardData={row.card}
                cardCount={row.card.length}
                title={row.title}
                key={index}
              />
            ))}
            <KanbanAddRowComponent AddRowCallBack={() => ""} />
            {provided.placeholder}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
};
export default KanbanBoardDragDropComponent;
