import { KANBAN_MANUES } from '@/src/constants/kanban/26';
import NavbarButtonComponent from '../button/Navbar.component';

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
