"use client";

import Txt from "@/components/common/Txt.component";
import HomeCardComponent from "@/components/main/Card.component";
import { MAIN_MENU, CURRENT_GENERATION } from "@/constants";

const HomePage = () => {
  let currentRecruitAfterString =
    CURRENT_GENERATION % 10 === 1
      ? "st"
      : CURRENT_GENERATION % 10 === 2
      ? "nd"
      : "th";

  return (
    <main className="flex justify-between">
      <div className="pt-24 pl-16 flex flex-col gap-2">
        <Txt className="text-6xl font-semibold uppercase">econovation</Txt>
        <Txt className="text-6xl font-semibold uppercase">recruit</Txt>
        <Txt className="text-6xl font-semibold">{`${CURRENT_GENERATION}${currentRecruitAfterString}`}</Txt>
      </div>
      <div className="grid grid-cols-3 absolute bottom-0 right-0">
        {MAIN_MENU.map((card, index) => (
          <HomeCardComponent
            title={card.title}
            subtitle={card.subtitle}
            href={card.href}
            key={index}
          />
        ))}
      </div>
    </main>
  );
};

export default HomePage;
