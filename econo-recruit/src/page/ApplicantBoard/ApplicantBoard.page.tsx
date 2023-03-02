import { useParams } from 'react-router';
import { useSearchParams } from 'react-router-dom';
import { ApplicationListMock } from '../../mock/MockData';
import ApplicantListElement from '../../components/Applicant/List.element';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import {
  applicantListState,
  applicantPopupBooleanState,
} from '../../storage/Applicant/Applicant.atom';
import { useEffect } from 'react';
import ApplicantPopupElement from '../../components/Applicant/Popup.element';
import ApplicantSortListComponent from '../../components/Applicant/SortList.component';
import ApplicantSearchComponent from '../../components/Applicant/Search.component';
import CommonNavbarComponent from '../../components/Navbar/Common.component';

type OrderType = 'resent' | 'name' | 'area';

const ApplicantBoardPage = () => {
  const { period } = useParams();
  const [searchParmas] = useSearchParams();
  const type = searchParmas.get('type') ?? 'list';
  const order = searchParmas.get('order') ?? 'newset';
  const page = searchParmas.get('page') ?? '1';

  const setApplicantBoardList = useSetRecoilState(applicantListState);
  const isPopuped = useRecoilValue(applicantPopupBooleanState);
  const orderMenu = [
    { type: 'newset', string: '최신순' },
    { type: 'name', string: '이름순' },
    { type: 'objective', string: '지원분야순' },
  ];

  useEffect(() => {
    setApplicantBoardList(ApplicationListMock);
  });

  return (
    <>
      {isPopuped ? <ApplicantPopupElement /> : ''}
      <div className="px-24 w-screen h-screen flex p-12">
        <CommonNavbarComponent />
        <div className="flex-1 ml-32 min-w-[46rem]">
          <div className="flex w-full justify-end gap-8 my-12">
            <ApplicantSearchComponent />
            <ApplicantSortListComponent sortList={orderMenu} />
          </div>
          <ApplicantListElement />
          <div className="flex w-full justify-end gap-2">
            {Array.from({ length: 4 }).map((_, i) => (
              <a
                href={`/applicant/${period}?type=${type}&order=${order}&page=${
                  i + 1
                }`}
                className={i + 1 === +page ? 'p-3' : 'text-[#B6B6B6] p-3'}
                key={i}
              >
                {i + 1}
              </a>
            ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default ApplicantBoardPage;
