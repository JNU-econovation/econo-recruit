"use client";

import { DragDropContext, DropResult, Droppable } from "@hello-pangea/dnd";
import { KanbanColumnData } from "@/src/stores/kanban/Kanban.atoms";
import { getMovedKanbanData } from "@/src/functions/kanban";
import KanbanAddColumnComponent from "./AddColumn.component";
import { useQuery } from "@tanstack/react-query";
import { FC, useState } from "react";
import { getAllKanbanData } from "@/src/apis/kanban";
import KanbanColumnComponent from "./Column.component";
import { useAtom } from "jotai";
import { KanbanSelectedButtonNumberState } from "@/src/stores/kanban/Navbar.atoms";

interface KanbanBoardDragDropProps {
  generation: string;
}

const KanbanBoardDragDropComponent: FC<KanbanBoardDragDropProps> = ({
  generation,
}) => {
  const [navbarId] = useAtom(KanbanSelectedButtonNumberState);

  const {
    data: kanbanData,
    isError,
    isLoading,
  } = useQuery<KanbanColumnData[]>(["kanbanDataArray", navbarId], () =>
    getAllKanbanData(navbarId)
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

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Droppable droppableId="droppable" type="card" direction="horizontal">
        {(provided) => (
          <div
            className="flex gap-4"
            ref={provided.innerRef}
            {...provided.droppableProps}
          >
            <KanbanColumnView kanbanData={kanbanData} />
            <KanbanAddColumnComponent AddColumnCallBack={() => {}} />
            {provided.placeholder}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
};
export default KanbanBoardDragDropComponent;

interface KanbanColumnViewProps {
  kanbanData: KanbanColumnData[];
}

const KanbanColumnView: FC<KanbanColumnViewProps> = ({ kanbanData }) => {
  return (
    <>
      {" "}
      {kanbanData.map((column, index) => (
        <KanbanColumnComponent
          key={index}
          title={column.title}
          columnCount={column.card.length}
          columnData={column.card}
          index={index}
        />
      ))}
    </>
  );
};
