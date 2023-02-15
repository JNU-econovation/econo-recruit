import { useParams } from 'react-router'
import { useSearchParams } from 'react-router-dom'
import { ApplicationListMock } from '../../Mock/MockData'
import ApplicantListElement from '../../Elements/Applicant/List.element'
import { useRecoilValue, useSetRecoilState } from 'recoil'
import {
  applicantListState,
  applicantPopupBooleanState,
} from '../../Atoms/Applicant/Applicant.atom'
import { useEffect } from 'react'
import ApplicantPopupElement from '../../Elements/Applicant/Popup.element'

type OrderType = 'resent' | 'name' | 'area'

const ApplicantBoardPage = () => {
  const { period } = useParams()
  const [searchParmas] = useSearchParams()
  const type = searchParmas.get('type') ?? 'list'
  const order = searchParmas.get('order') ?? 'resent'
  const page = searchParmas.get('page') ?? '1'

  const setApplicantBoardList = useSetRecoilState(applicantListState)
  const isPopuped = useRecoilValue(applicantPopupBooleanState)
  const orderMenu = [
    { type: 'resent', string: '최신순' },
    { type: 'name', string: '이름순' },
    { type: 'area', string: '지원분야순' },
  ]

  useEffect(() => {
    setApplicantBoardList(ApplicationListMock)
  })

  return (
    <>
      {isPopuped ? <ApplicantPopupElement /> : ''}
      <div className="px-24 w-screen h-screen">
        <div className="pt-24 pb-6 border-b-4 flex justify-between items-baseline">
          <div className="text-3xl font-bold">{period}기 신입모집</div>
          <div className="flex gap-4 text-lg">
            <a
              href={`/applicant/${period}?type=stats&order=${order}&page=${page}`}
              className={type === 'stats' ? 'border-b-[1px] border-[#0047FF]' : ''}
            >
              통계
            </a>
            <a
              href={`/applicant/${period}?type=list&order=${order}&page=${page}`}
              className={type === 'list' ? 'border-b-[1px] border-[#0047FF]' : ''}
            >
              리스트
            </a>
          </div>
        </div>
        <div className="flex py-6">
          <div className="flex gap-6 text-lg">
            {orderMenu.map((menu) => (
              <a
                href={`/applicant/${period}?type=${type}&order=${menu.type}&page=${page}`}
                className={menu.type === order ? 'border-b-[1px] border-[#0047FF]' : ''}
                key={menu.type}
              >
                {menu.string}
              </a>
            ))}
          </div>
        </div>
        <ApplicantListElement />
        <div className="flex w-full justify-center gap-2">
          {Array.from({ length: 4 }).map((_, i) => (
            <a
              href={`/applicant/${period}?type=${type}&order=${order}&page=${i + 1}`}
              className={i + 1 === +page ? 'p-3' : 'text-[#B6B6B6] p-3'}
              key={i}
            >
              {i + 1}
            </a>
          ))}
        </div>
      </div>
    </>
  )
}

export default ApplicantBoardPage
