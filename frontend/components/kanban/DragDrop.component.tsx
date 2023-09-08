"use client";

import { DragDropContext, DropResult, Droppable } from "@hello-pangea/dnd";
import { KanbanColumnData } from "@/src/stores/kanban/Kanban.atoms";
import {
  getFromToIndexColumn,
  getFromToIndexDefault,
  getMovedKanbanData,
} from "@/src/functions/kanban";
import KanbanAddColumnComponent from "./column/AddColumn.component";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { FC } from "react";
import { getAllKanbanData } from "@/src/apis/kanban/kanban";
import KanbanColumnComponent from "./column/Column.component";
import { useAtomValue } from "jotai";
import { KanbanSelectedButtonNumberState } from "@/src/stores/kanban/Navbar.atoms";
import { postLocations, putColumnsLocations } from "@/src/apis/kanban/location";

const KanbanColumnView = () => {
  const navbarId = useAtomValue(KanbanSelectedButtonNumberState);

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
  return (
    <>
      {kanbanData.map((column, index) => (
        <KanbanColumnComponent
          key={index}
          title={column.title}
          columnCount={column.card.length - 1}
          columnData={column.card}
          columnIndex={index}
          columnId={column.id}
        />
      ))}
    </>
  );
};

interface KanbanBoardDragDropProps {
  generation: string;
}

const KanbanBoardDragDropComponent: FC<KanbanBoardDragDropProps> = ({
  generation,
}) => {
  const navbarId = useAtomValue(KanbanSelectedButtonNumberState);
  const queryClient = useQueryClient();

  const { mutate: relocation } = useMutation(postLocations, {
    onSettled: () => {
      queryClient.invalidateQueries({
        queryKey: ["kanbanDataArray", navbarId],
      });
    },
  });

  const { mutate: relocationColumn } = useMutation(putColumnsLocations, {
    onSettled: () => {
      queryClient.invalidateQueries({
        queryKey: ["kanbanDataArray", navbarId],
      });
    },
  });

  const onDragEnd = (result: DropResult) => {
    const kanbanData = queryClient.getQueryData<KanbanColumnData[]>([
      "kanbanDataArray",
      navbarId,
    ]) as KanbanColumnData[];

    if (result.type === "card") {
      relocationColumn(getFromToIndexColumn(kanbanData, result), {
        onSuccess: () => {
          queryClient.setQueryData<KanbanColumnData[]>(
            ["kanbanDataArray", navbarId],
            (oldData) => {
              return getMovedKanbanData(oldData as KanbanColumnData[], result);
            }
          );
        },
      });
    }

    if (result.type === "DEFAULT") {
      relocation(getFromToIndexDefault(kanbanData, result), {
        onSuccess: () => {
          queryClient.setQueryData<KanbanColumnData[]>(
            ["kanbanDataArray", navbarId],
            (oldData) => {
              return getMovedKanbanData(oldData as KanbanColumnData[], result);
            }
          );
        },
      });
    }
  };

  return (
    <DragDropContext onDragEnd={onDragEnd}>
      <Droppable droppableId="droppable" type="card" direction="horizontal">
        {(provided) => (
          <div
            key={navbarId}
            className="flex gap-4"
            ref={provided.innerRef}
            {...provided.droppableProps}
          >
            <KanbanColumnView />
            <KanbanAddColumnComponent AddColumnCallBack={() => {}} />
            {provided.placeholder}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
};
export default KanbanBoardDragDropComponent;
