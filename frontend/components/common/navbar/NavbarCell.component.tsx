import Image from 'next/image';
import LtIcon from '@/public/icons/lt.icon.svg';
import LtIconWhite from '@/public/icons/lt.icon.white.svg';

type CommonNavbarCellComponent = {
  currentPath: string;
  item: {
    type: string;
    href: string;
    target: string;
    short_title: string;
    title: string;
  };
  isShort: boolean;
};

const CommonNavbarCellComponent = ({
  currentPath,
  item,
  isShort,
}: CommonNavbarCellComponent) => {
  const linkButtonClassName =
    'flex justify-between p-4 hover:bg-[#B9B9B9] hover:text-white rounded-lg';
  return (
    <a
      className={
        currentPath === item.type
          ? `${linkButtonClassName} !bg-black !text-white`
          : linkButtonClassName
      }
      href={item.href}
      target={item.target === '_blank' ? '_blank' : ''}
      key={item.type}
    >
      {isShort ? item.short_title : item.title}
      <Image
        src={currentPath !== item.type ? LtIcon : LtIconWhite}
        alt="right arrow"
      />
    </a>
  );
};

export default CommonNavbarCellComponent;
