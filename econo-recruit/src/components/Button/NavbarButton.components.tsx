import { useRecoilState } from 'recoil';
import { KanbanSelectedButtonNumberState } from '../../storage/KanbanBoard/Navbar.atoms';
import { KANBAN_MANUES } from '../../data/25/KanbanMenus';

type NavbarButtonComponent = { value: string };

const NavbarButtonComponent = ({ value }: NavbarButtonComponent) => {
  const findManueIndex = KANBAN_MANUES.findIndex((manue) => manue === value);

  const [selected, setSelected] = useRecoilState(
    KanbanSelectedButtonNumberState
  );
  const buttonClassName = ' py-2 px-6 rounded-lg min-w-fit ';

  const onClick = () => {
    setSelected(findManueIndex);
  };

  return (
    <button
      className={
        findManueIndex === selected
          ? 'bg-gray-800 text-white' + buttonClassName
          : 'bg-white text-gray-400' + buttonClassName
      }
      onClick={onClick}
    >
      {value}
    </button>
  );
};

export default NavbarButtonComponent;
