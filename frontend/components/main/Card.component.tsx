"use client";

import Image from "next/image";
import { useState } from "react";
import RightArrow from "@/public/icons/right.arrow.svg";
import RightArrowWhite from "@/public/icons/right.arrow.white.svg";
import Txt from "@/components/common/Txt.component";
import Link from "next/link";

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
    <Link
      href={href}
      className="h-[45vh] w-[22vw] min-w-[20rem] min-h-[14rem] pt-16 p-12 border-l-[1px] border-t-[1px] flex flex-col justify-between bg-white hover:bg-black"
      onMouseEnter={() => setIsHover(true)}
      onMouseLeave={() => setIsHover(false)}
    >
      <div>
        <Txt
          typography="h3"
          color={isHover ? "white" : "black"}
          className="block"
        >
          {title}
        </Txt>
        <Txt
          typography="h4"
          color={isHover ? "white" : "black"}
          className="font-normal"
        >
          {subtitle}
        </Txt>
      </div>
      <div className="w-full flex flex-row-reverse">
        <Image
          className="w-fit h-fit"
          src={isHover ? RightArrowWhite : RightArrow}
          alt="enter arrow"
        />
      </div>
    </Link>
  );
};

export default HomeCardComponent;
