"use client";

import { DragDropContext, DropResult, Droppable } from "@hello-pangea/dnd";
import { KanbanColumnData } from "@/src/stores/kanban/Kanban.atoms";
import { getMovedKanbanData } from "@/src/functions/kanban";
import KanbanAddColumnComponent from "./AddColumn.component";
import { useQuery } from "@tanstack/react-query";
import { FC, useEffect } from "react";
import { getAllKanbanData } from "@/src/apis/kanban";
import KanbanColumnComponent from "./Column.component";

interface KanbanBoardDragDropProps {
  generation: string;
}

const KanbanBoardDragDropComponent: FC<KanbanBoardDragDropProps> = ({
  generation,
}) => {
  const {
    data: kanbanData,
    isError,
    isLoading,
  } = useQuery<KanbanColumnData[]>(["kanbanDataArray", generation], () =>
    getAllKanbanData("1")
  );

  if (!kanbanData || isLoading) {
    return <div>로딩중...</div>;
  }

  if (isError) {
    return <div>에러 발생</div>;
  }

  const onDragEnd = (result: DropResult) => {
    const movedKanbanData = getMovedKanbanData(kanbanData, result);
  };

  console.log(kanbanData);

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Droppable droppableId="droppable" type="card" direction="horizontal">
        {(provided) => (
          <div
            className="flex gap-4"
            ref={provided.innerRef}
            {...provided.droppableProps}
          >
            {kanbanData.map((column, index) => (
              <KanbanColumnComponent
                key={index}
                title={column.title}
                columnCount={column.card.length}
                columnData={column.card}
                index={index}
              />
            ))}
            <KanbanAddColumnComponent AddColumnCallBack={() => ""} />
            {provided.placeholder}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
};
export default KanbanBoardDragDropComponent;
