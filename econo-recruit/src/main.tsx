import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { RecoilRoot } from 'recoil'
import { RouterProvider } from 'react-router'
import { createBrowserRouter } from 'react-router-dom'
import KanbanBoardPage from './Pages/KanbanBoard/KanbanBoard.page'
import KanbanDetailPage from './Pages/KanbanBoard/KanbanDetail.page'

const router = createBrowserRouter([
  {
    path: '/',
    element: <div>asdf</div>,
  },
  {
    path: '/kanban/:period',
    children: [
      { path: '/kanban/:period', element: <KanbanBoardPage /> },
      { path: '/kanban/:period/detail', element: <KanbanDetailPage /> },
    ],
  },
])

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RecoilRoot>
      <RouterProvider router={router} />
    </RecoilRoot>
  </React.StrictMode>
)
