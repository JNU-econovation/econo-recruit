import HomeCardComponent from '../../components/Home/Card.component';
import { MAIN_MENU } from '../../data/25/MainMenus';

const HomePage = () => {
  const currentRecruit = 25;
  let currentRecruitAfterString =
    currentRecruit % 10 === 1 ? 'st' : currentRecruit % 10 === 2 ? 'nd' : 'th';

  return (
    <div className="flex justify-between">
      <div className="pt-24 pl-16 text-6xl font-semibold flex flex-col gap-2 ">
        <div>ECONOVATION</div>
        <div>RECRUIT</div>
        <div>
          {currentRecruit}
          {currentRecruitAfterString}
        </div>
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
    </div>
  );
};

export default HomePage;
