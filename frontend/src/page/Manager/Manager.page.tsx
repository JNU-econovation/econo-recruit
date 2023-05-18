import { useParams } from 'react-router';
import CommonNavbarComponent from '@/components/Common/Navbar.component';
import { useSearchParams } from 'react-router-dom';
import ManagerSearchComponent from '@/components/Manager/Search.component';
import ManagerSortListComponent from '@/components/Manager/SortList.component';
import { useEffect } from 'react';
import { ManagerListMock } from '@/mock/MockData';
import { managerListState } from '@/storage/Manager/Manager.atom';
import ManagerListComponent from '@/components/Manager/List.component';
import { useSetAtom } from 'jotai';

const ManagerPage = () => {
  const { period } = useParams();
  const [searchParmas] = useSearchParams();
  const type = searchParmas.get('type') ?? 'list';
  const order = searchParmas.get('order') ?? 'newset';
  const page = searchParmas.get('page') ?? '1';
  const setManagerList = useSetAtom(managerListState);

  const orderMenu = [
    { type: 'newset', string: '최신순' },
    { type: 'name', string: '이름순' },
  ];

  useEffect(() => {
    setManagerList(ManagerListMock);
  });

  return (
    <div className="px-24 w-screen h-screen flex p-12">
      <CommonNavbarComponent />
      <div className="flex-1 ml-32 min-w-[46rem] mb-48">
        <div className="flex w-full justify-end gap-8 my-12">
          <ManagerSearchComponent />
          <ManagerSortListComponent sortList={orderMenu} />
        </div>
        <ManagerListComponent />
        <div className="flex w-full justify-end gap-2 absolute bottom-12 right-24">
          {Array.from({ length: 4 }).map((_, i) => (
            <a
              href={`/manager/${period}?type=${type}&order=${order}&page=${
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
  );
};

export default ManagerPage;
