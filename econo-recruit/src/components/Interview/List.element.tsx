import { useRecoilValue, useSetRecoilState } from 'recoil';
import {
  interviewListState,
  interviewPopupBooleanState,
} from '../../storage/Interview/Interview.atom';

const InterviewListElement = () => {
  const boardData = useRecoilValue(interviewListState);
  const setIsPopuped = useSetRecoilState(interviewPopupBooleanState);
  const onBoardClick = () => {
    setIsPopuped(true);
  };

  return (
    <div className="flex flex-col text-lg">
      {boardData.map((board, index) => (
        <button
          className="flex h-[calc(14vh-3rem)] min-h-[2rem] border-t-[1px] border-[#B9B9B9]"
          onClick={onBoardClick}
          key={index}
        >
          <div className="h-full w-full flex items-center justify-between">
            <div className="font-semibold">{board.title}</div>
            <div className="flex gap-16 text-[#8C8C8C]">
              <div className="flex gap-8">
                {board.apply.map((a) => (
                  <div key={a}>{a}</div>
                ))}
              </div>
              <div className="flex gap-16">
                <div>{board.score}</div>
                <div>{board.registerDate}</div>
              </div>
            </div>
          </div>
        </button>
      ))}
    </div>
  );
};

export default InterviewListElement;
