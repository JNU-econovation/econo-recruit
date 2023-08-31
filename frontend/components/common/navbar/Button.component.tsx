"use client";

import { useAtom } from "jotai";
import { KanbanSelectedButtonNumberState } from "@/src/stores/kanban/Navbar.atoms";
import { KANBAN_MENU } from "@/src/constants/kanban/26";

type NavbarButtonComponent = { value: string };

const NavbarButtonComponent = ({ value }: NavbarButtonComponent) => {
  const findManueIndex = KANBAN_MENU.findIndex(
    (menu) => menu.navTitle === value
  );

  const [selected, setSelected] = useAtom(KanbanSelectedButtonNumberState);
  const buttonClassName = " py-2 px-6 rounded-lg min-w-fit ";

  const onClick = () => {
    setSelected(findManueIndex);
  };

  return (
    <button
      className={
        findManueIndex === selected
          ? `bg-[#303030] text-white${buttonClassName}`
          : `bg-white text-gray-400${buttonClassName}`
      }
      onClick={onClick}
    >
      {value}
    </button>
  );
};

export default NavbarButtonComponent;
