import { lazy, useEffect } from 'react'
import ApplicationNavbarElement from '../../Elements/Application/Navbar.element'
import { useParams, useSearchParams } from 'react-router-dom'
import { useRecoilState } from 'recoil'
import { ApplicationNavbarIndexState } from '../../Atoms/Application/ApplicationNavbar.atom'

const ApplicationQuestionReport1 = lazy(
  () => import('../../Elements/Application/Question/Repost1.element')
)

const ApplicationPage = () => {
  const { period } = useParams()
  const [pageIndex, setPageIndex] = useRecoilState(ApplicationNavbarIndexState)
  const page = [<ApplicationQuestionReport1 />]

  return (
    <div className="pt-24 flex">
      <div className="pl-12 w-[26rem]">
        <ApplicationNavbarElement />
      </div>
      <div className="pl-32 w-full">
        <div className="pb-6 text-3xl font-bold border-b-4 w-full">{period}기 신입모집</div>
        <div className="pt-6">{page[pageIndex]}</div>
      </div>
    </div>
  )
}

export default ApplicationPage
