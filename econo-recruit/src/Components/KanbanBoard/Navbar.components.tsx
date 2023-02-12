import { KANBAN_MANUES } from '../../Data/25/KanbanMenus'
import NavbarButtonComponent from '../Button/NavbarButton.components'

const KanbanNavbarComponent = () => {
  return (
    <>
      {KANBAN_MANUES.map((manue) => (
        <NavbarButtonComponent key={manue} value={manue} />
      ))}
    </>
  )
}

export default KanbanNavbarComponent
