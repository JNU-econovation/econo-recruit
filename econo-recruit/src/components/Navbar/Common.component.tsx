import { useParams } from 'react-router';
import { MainNavbar } from '../../data/25/MainNavbar';

type CommonNavbarComponent = {
  isShort?: boolean;
};

const CommonNavbarComponent = ({ isShort = false }: CommonNavbarComponent) => {
  const currentUrl = document.location.pathname;
  const currentPath = currentUrl.split('/')[1];

  const { period } = useParams();

  const linkButtonClassName =
    'flex justify-between p-4 hover:bg-[#B9B9B9] hover:text-white rounded-lg';
  return (
    <div className="flex flex-col">
      <a className="font-bold text-4xl" href="/">
        {isShort ? '' : <div>ECONOVATION</div>}
        <div>RECRUIT</div>
        <div>{period}th</div>
      </a>
      <div className="flex flex-col gap-8 mt-8 text-xl">
        {MainNavbar.map((item) => (
          <a
            className={
              currentPath === item.type
                ? linkButtonClassName + ' !bg-black !text-white'
                : linkButtonClassName
            }
            href={item.href}
            target={item.target === '_blank' ? '_blank' : ''}
            key={item.type}
          >
            {isShort ? item.short_title : item.title}
            <img src="/lt.icon.svg" alt="right arrow" />
          </a>
        ))}
      </div>
    </div>
  );
};

export default CommonNavbarComponent;
