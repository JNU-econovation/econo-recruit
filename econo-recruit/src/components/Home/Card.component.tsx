import { useState } from 'react';

type HomeCardComponentType = {
  title: string;
  subtitle: string;
  href: string;
};

const HomeCardComponent = ({
  title,
  subtitle,
  href,
}: HomeCardComponentType) => {
  const [isHover, setIsHover] = useState(false);
  return (
    <a
      href={href}
      className="h-[45vh] w-[22vw] min-w-[20rem] min-h-[14rem] pt-16 p-12 border-l-[1px] border-t-[1px] flex flex-col justify-between bg-white hover:bg-black hover:text-white"
      onMouseEnter={() => setIsHover(true)}
      onMouseLeave={() => setIsHover(false)}
    >
      <div>
        <div className="font-bold text-2xl">{title}</div>
        <div className="text-lg">{subtitle}</div>
      </div>
      <div className="w-full flex flex-row-reverse">
        <img
          className="w-fit h-fit"
          src={isHover ? '/right.arrow.white.svg' : '/right.arrow.svg'}
          alt="enter arrow"
        />
      </div>
    </a>
  );
};

export default HomeCardComponent;
