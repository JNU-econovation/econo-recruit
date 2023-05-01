import { KANBAN_MANUES } from '@/data/25/KanbanMenus';
import NavbarButtonComponent from '../Button/NavbarButton.components';

const KanbanNavbarComponent = () => {
  return (
    <>
      {KANBAN_MANUES.map((manue: string) => (
        <NavbarButtonComponent key={manue} value={manue} />
      ))}
    </>
  );
};

export default KanbanNavbarComponent;
