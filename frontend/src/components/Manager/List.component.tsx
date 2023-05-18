import { useAtomValue, useSetAtom } from 'jotai';
import {
  managerListState,
  managerPopupBooleanState,
} from '@/storage/Manager/Manager.atom';

const ManagerListComponent = () => {
  const boardData = useAtomValue(managerListState);
  const setIsPopuped = useSetAtom(managerPopupBooleanState);
  const onBoardClick = () => {
    setIsPopuped(true);
  };

  const authorityType = ['회장단', '운영팀', 'TF'] as const;
  const authorityClassName = 'px-6 py-2 rounded-lg h-fit w-fit';

  return (
    <div className="flex flex-col text-lg">
      <div className="h-full w-full flex items-center justify-between text-[#B5B7C0]">
        <div className="font-semibold flex">
          <div className="w-36 mx-12 mb-4">Memeber Name</div>
          <div>기수</div>
        </div>
        <div>Status</div>
      </div>

      {boardData.map((board, index) => (
        <div
          className="flex h-[calc(14vh-3rem)] min-h-[2rem] border-t-[1px] border-[#B9B9B9]"
          onClick={onBoardClick}
          key={index}
        >
          <div className="h-full w-full flex items-center justify-between">
            <div className="font-semibold flex">
              <div className="w-24 mr-24 ml-12">{board.name}</div>
              <div>{board.period}기</div>
            </div>
            <div className="flex gap-8 h-fit">
              {authorityType.map((authority) => (
                <button
                  className={
                    'bg-[#EDEDED] text-[#B5B5B5] ' + authorityClassName
                  }
                >
                  {authority}
                </button>
              ))}
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ManagerListComponent;
