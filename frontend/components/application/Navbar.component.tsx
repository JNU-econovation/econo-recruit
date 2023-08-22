"use client";

import { applicationIndexAtom } from "@/src/stores/application";
import { useAtom } from "jotai";
import { FC } from "react";
import classNames from "classnames";
<<<<<<< HEAD:frontend/components/application/ApplicationNavbar.component.tsx
import { APPLICATION } from "@/src/constants/application/25";
import Txt from "../common/Txt.component";
=======
import Txt from "@/components/common/Txt.component";
>>>>>>> 53f55f8e1ac8cac70e463a946d77a65fb8cf1d58:frontend/components/application/Navbar.component.tsx

interface ApplicationNavbarProps {
  generation: string;
  className?: string;
}

const ApplicationNavbar: FC<ApplicationNavbarProps> = ({
  generation,
  className,
}) => {
  const [applicationIndex, setApplicationIndex] = useAtom(applicationIndexAtom);
  const applicationNavbar =
    require(`@/src/constants/application/${generation}.ts`)
      .APPLICATION_NAVBAR as {
      id: number;
      title: string;
    }[];

  return (
    <nav className={classNames("pl-12 w-full h-full", className)}>
      {applicationNavbar.map((navItem, index) => (
        <button
          className={"text-left p-4 relative"}
          onClick={() => setApplicationIndex(navItem.id)}
          key={navItem.id}
        >
          {/* 마지막 선은 그리지 않기 */}
          {index !== applicationNavbar.length - 1 && (
            <div
              className={classNames(
                "absolute border-l-2 h-full -left-[13px] top-8 -z-10",
                applicationIndex > navItem.id
                  ? "border-black"
                  : "border-gray-300"
              )}
            ></div>
          )}
          <Txt
            className={classNames(
              "relative transition-all before:h-2 before:w-2 before:rounded-full before:absolute before:translate-y-full before:-translate-x-8",
              applicationIndex >= navItem.id
                ? "before:bg-black text-black"
                : "before:bg-gray-300 text-gray-300"
            )}
          >
            {navItem.title}
          </Txt>
        </button>
      ))}
    </nav>
  );
};

export default ApplicationNavbar;
