import { Draggable } from "@hello-pangea/dnd";
import { FC } from "react";

interface KanbanColumnInvisible {
  columnIndex: number;
  index: number;
}

const KanbanColumnInvisible: FC<KanbanColumnInvisible> = ({
  columnIndex,
  index,
}) => {
  return (
    <Draggable draggableId={`${columnIndex}-${index}`} index={columnIndex}>
      {(provided) => (
        <div
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
          className="py-1"
        ></div>
      )}
    </Draggable>
  );
};

export default KanbanColumnInvisible;
