import { useParams } from 'react-router';
import { useSearchParams } from 'react-router-dom';
import { ApplicationListMock } from '@/mock/MockData';
import ApplicantListComponent from '@/components/Applicant/List.component';
import {
  applicantListState,
  applicantPopupBooleanState,
} from '@/storage/Applicant/Applicant.atom';
import { useEffect } from 'react';
import ApplicantPopupComponent from '@/components/Applicant/Popup.component';
import ApplicantSortListComponent from '@/components/Applicant/SortList.component';
import ApplicantSearchComponent from '@/components/Applicant/Search.component';
import CommonNavbarComponent from '@/components/Common/Navbar.component';
import { useAtomValue, useSetAtom } from 'jotai';

type OrderType = 'resent' | 'name' | 'area';

const ApplicantBoardPage = () => {
  const { period } = useParams();
  const [searchParmas] = useSearchParams();
  const type = searchParmas.get('type') ?? 'list';
  const order = searchParmas.get('order') ?? 'newset';
  const page = searchParmas.get('page') ?? '1';

  const setApplicantBoardList = useSetAtom(applicantListState);
  const isPopuped = useAtomValue(applicantPopupBooleanState);
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
      {isPopuped ? <ApplicantPopupComponent /> : ''}
      <div className="px-24 w-screen h-screen flex p-12">
        <CommonNavbarComponent />
        <div className="flex-1 ml-32 min-w-[46rem]">
          <div className="flex w-full justify-end gap-8 my-12">
            <ApplicantSearchComponent />
            <ApplicantSortListComponent sortList={orderMenu} />
          </div>
          <ApplicantListComponent />
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
