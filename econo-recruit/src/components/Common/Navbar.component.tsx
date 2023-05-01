import { useParams } from 'react-router';
import { MainNavbar } from '@/data/25/MainNavbar';
import { userInformationState } from '@/storage/Common/user.atom';
import CommonNavbarCellComponent from './NavbarCell.component';
import { useAtomValue } from 'jotai';

type CommonNavbarComponent = {
  isShort?: boolean;
};

const CommonNavbarComponent = ({ isShort = false }: CommonNavbarComponent) => {
  const userData = useAtomValue(userInformationState);
  const currentUrl = document.location.pathname;
  const currentPath = currentUrl.split('/')[1];

  const { period } = useParams();

  return (
    <div className="flex flex-col">
      <a className="font-bold text-4xl" href="/">
        {isShort ? '' : <div>ECONOVATION</div>}
        <div>RECRUIT</div>
        <div>{period}th</div>
      </a>
      <div className="flex flex-col gap-8 mt-8 text-xl">
        {MainNavbar.map((item) => (
          <CommonNavbarCellComponent
            currentPath={currentPath}
            isShort={isShort}
            item={item}
          />
        ))}
        {userData.authority === 'chairman' ? (
          <CommonNavbarCellComponent
            currentPath={currentPath}
            isShort={isShort}
            item={{
              href: '/manager/' + period,
              short_title: '관리자',
              title: '관리자 페이지',
              target: '_self',
              type: 'manager',
            }}
          />
        ) : (
          ''
        )}
      </div>
    </div>
  );
};

export default CommonNavbarComponent;
