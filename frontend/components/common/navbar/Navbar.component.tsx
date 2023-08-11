"use client";

import { MainNavbar } from "@/constants";
import { FC } from "react";
import CommonNavbarCellComponent from "./NavbarCell.component";

interface CommonNavbarProps {
  generation: string;
  isShort?: boolean;
}

const CommonNavbar: FC<CommonNavbarProps> = ({
  generation,
  isShort = false,
}) => {
  const userData = { authority: "" };
  const currentUrl = document.location.pathname;
  const currentPath = currentUrl.split("/")[1];

  return (
    <nav className="flex flex-col">
      <a className="font-bold text-4xl" href="/">
        {isShort ? "" : <div>ECONOVATION</div>}
        <div>RECRUIT</div>
        <div>{generation}th</div>
      </a>
      <div className="flex flex-col gap-8 mt-8 text-xl">
        {MainNavbar.map((item) => (
          <CommonNavbarCellComponent
            key={item.type}
            currentPath={currentPath}
            isShort={isShort}
            item={item}
          />
        ))}
        {userData.authority === "chairman" ? (
          <CommonNavbarCellComponent
            currentPath={currentPath}
            isShort={isShort}
            item={{
              href: "/manager/" + generation,
              short_title: "관리자",
              title: "관리자 페이지",
              target: "_self",
              type: "manager",
            }}
          />
        ) : (
          ""
        )}
      </div>
    </nav>
  );
};

export default CommonNavbar;
