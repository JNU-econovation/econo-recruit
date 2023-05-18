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
import ApplicantSearchComponent from '@/components/Applicant/Search.component';
import CommonNavbarComponent from '@/components/Common/Navbar.component';
import { useAtomValue, useSetAtom } from 'jotai';
import PageNavbarComponent from '@/components/Common/PageNavbar.component';
import SortListComponent from '@/components/Common/SortList.component';

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
            <SortListComponent sortList={orderMenu} selected={order} />
          </div>
          <ApplicantListComponent />
          <PageNavbarComponent
            maxLength={4}
            page={+page}
            url={`/applicant/${period}?type=${type}&order=${order}`}
          />
        </div>
      </div>
    </>
  );
};

export default ApplicantBoardPage;
