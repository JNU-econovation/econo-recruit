import {
  DragDropContext,
  DropResult,
  Droppable,
} from '@hello-pangea/dnd';
import KanbanRowComponent from './Row.components';
import KanbanAddRowComponent from './AddRow.component';
import { KanbanDataArrayState, KanbanRowData } from '@/storage/KanbanBoard/Kanban.atoms';
import { useAtom } from 'jotai';
import { getMovedKanbanData } from '@/page/KanbanBoard/kanbanBoardEvent';

const KanbanBoardDragDropComponent = () => {
  const [kanbanData, setKanbanData] = useAtom(KanbanDataArrayState);
  const onDragEnd = (result: DropResult) => {
    const movedKanbanData = getMovedKanbanData(kanbanData, result);
    setKanbanData(movedKanbanData);
  };

  return (<DragDropContext onDragEnd={onDragEnd}>
    <Droppable droppableId="droppable" type="COLUMN" direction="horizontal">
      {(provided) => (
        <div
          className="flex gap-4"
          ref={provided.innerRef}
          {...provided.droppableProps}
        >
          {kanbanData.map((row, index) => (
            <KanbanRowComponent
              index={index}
              columnData={row.column}
              columnCount={row.column.length}
              title={row.title}
              key={index}
            />
          ))}
          <KanbanAddRowComponent AddRowCallBack={() => ''} />
          {provided.placeholder}
        </div>
      )}
    </Droppable>
  </DragDropContext>);
}
export default KanbanBoardDragDropComponent;
