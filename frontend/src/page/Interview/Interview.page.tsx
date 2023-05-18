import { useParams } from 'react-router';
import { useSearchParams } from 'react-router-dom';
import { InterviewListMock } from '@/mock/MockData';
import InterviewListComponent from '@/components/Interview/List.component';
import {
  interviewListState,
  interviewPopupBooleanState,
} from '@/storage/Interview/Interview.atom';
import { useEffect } from 'react';
import InterviewPopupComponent from '@/components/Interview/Popup.component';
import InterviewSearchComponent from '@/components/Interview/Search.component';
import CommonNavbarComponent from '@/components/Common/Navbar.component';
import { useAtomValue, useSetAtom } from 'jotai';
import PageNavbarComponent from '@/components/Common/PageNavbar.component';
import SortListComponent from '@/components/Common/SortList.component';

const InterviewPage = () => {
  const { period } = useParams();
  const [searchParmas] = useSearchParams();
  const type = searchParmas.get('type') ?? 'list';
  const order = searchParmas.get('order') ?? 'newset';
  const page = searchParmas.get('page') ?? '1';

  const setInterviewList = useSetAtom(interviewListState);
  const isPopuped = useAtomValue(interviewPopupBooleanState);
  const orderMenu = [
    { type: 'newset', string: '최신순' },
    { type: 'name', string: '이름순' },
    { type: 'objective', string: '지원분야순' },
    { type: 'score', string: '점수순' },
  ];

  useEffect(() => {
    setInterviewList(InterviewListMock);
  });

  return (
    <>
      {isPopuped ? <InterviewPopupComponent /> : ''}
      <div className="px-24 w-screen h-screen flex p-12">
        <CommonNavbarComponent />
        <div className="flex-1 ml-32 min-w-[46rem] mb-48">
          <div className="flex w-full justify-end gap-8 my-12">
            <InterviewSearchComponent />
            <SortListComponent sortList={orderMenu} selected={order} />
          </div>
          <InterviewListComponent />
          <PageNavbarComponent
            maxLength={4}
            page={+page}
            url={`/interview/${period}?type=${type}&order=${order}`}
          />
        </div>
      </div>
    </>
  );
};

export default InterviewPage;
