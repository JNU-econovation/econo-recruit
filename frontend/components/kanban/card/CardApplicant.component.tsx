"use client";

import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import { Draggable } from "@hello-pangea/dnd";
import { FC } from "react";
import KanbanCardComponent from "./Card.component";

interface KanbanColumnApplicant {
  columnIndex: number;
  index: number;
  column: KanbanCardData;
}

const KanbanColumnApplicant: FC<KanbanColumnApplicant> = ({
  index,
  columnIndex,
  column,
}) => {
  return (
    <Draggable draggableId={`${index}-${column?.id}`} index={column.id}>
      {(provided) => (
        <div
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
          className="my-4"
        >
          <KanbanCardComponent data={column} columnIndex={columnIndex} />
        </div>
      )}
    </Draggable>
  );
};

export default KanbanColumnApplicant;
