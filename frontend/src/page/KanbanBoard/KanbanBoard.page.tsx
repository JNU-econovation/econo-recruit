import { useParams } from 'react-router';
import KanbanNavbarComponent from '@/components/KanbanBoard/Navbar.components';
import { DropResult } from '@hello-pangea/dnd';
import { KanbanDataArrayState } from '@/storage/KanbanBoard/Kanban.atoms';
import CommonNavbarComponent from '@/components/Common/Navbar.component';
import { getMovedKanbanData } from './kanbanBoardEvent';
import { useAtom } from 'jotai';
import KanbanBoardDragDropComponent from '@/components/KanbanBoard/DragDrop.component';

const KanbanBoardPage = () => {
  const { period } = useParams();
  const [kanbanData, setKanbanData] = useAtom(KanbanDataArrayState);
  const onDragEnd = (result: DropResult) => {
    const movedKanbanData = getMovedKanbanData(kanbanData, result);
    setKanbanData(movedKanbanData);
  };

  return (
    <div className="pt-12 pl-24 w-screen h-screen flex">
      <CommonNavbarComponent isShort={true} />
      <div className="ml-32 overflow-auto">
        <div className="pt-12 pb-6 text-3xl font-bold border-b-4">
          {period}기 신입모집
        </div>
        <div className="flex gap-4 py-6 font-medium overflow-auto">
          <KanbanNavbarComponent />
        </div>
        <div className="overflow-auto max-h-[calc(100vh-18rem)]">
          <KanbanBoardDragDropComponent
            onDragEnd={onDragEnd}
            kanbanData={kanbanData}
          />
        </div>
      </div>
    </div>
  );
};

export default KanbanBoardPage;
