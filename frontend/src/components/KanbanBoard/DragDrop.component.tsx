import {
  DragDropContext,
  DropResult,
  Droppable,
  OnDragEndResponder,
} from '@hello-pangea/dnd';
import KanbanRowComponent from './Row.components';
import KanbanAddRowComponent from './AddRow.component';
import { KanbanRowData } from '@/storage/KanbanBoard/Kanban.atoms';

type KanbanBoardDragDropComponent = {
  onDragEnd: OnDragEndResponder;
  kanbanData: KanbanRowData[];
};

const KanbanBoardDragDropComponent = ({
  onDragEnd,
  kanbanData,
}: KanbanBoardDragDropComponent) => (
  <DragDropContext onDragEnd={onDragEnd}>
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
  </DragDropContext>
);
export default KanbanBoardDragDropComponent;
