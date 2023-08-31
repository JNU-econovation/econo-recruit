import { KANBAN_MENU, KanbanMenu } from "@/src/constants/kanban/26";
import NavbarButtonComponent from "../common/navbar/Button.component";

const KanbanNavbarComponent = () => {
  return (
    <>
      {KANBAN_MENU.map((menu: KanbanMenu) => (
        <NavbarButtonComponent key={menu.id} value={menu.navTitle} />
      ))}
    </>
  );
};

export default KanbanNavbarComponent;
