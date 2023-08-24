type InterviewCommentCellComponent = {
  item: {
    name: string;
    passion: number;
    action: number;
    collaboration: number;
    focus: number;
    eager: number;
  };
};

const InterviewCommentCellComponent = ({
  item,
}: InterviewCommentCellComponent) => {
  return (
    <div className="flex items-center gap-[6%]">
      <div className="flex min-w-[6.7rem] justify-center items-center">
        <div className="text-[#2160FF] bg-[#E8EFFF] px-5 py-2 rounded-3xl max-w-fit">
          {item.name}
        </div>
      </div>

      <div className="flex w-full text-xl gap-[3%]">
        <span className="flex min-w-[4.1rem] justify-center items-center">
          {item.passion}
        </span>
        <span className="flex min-w-[4.1rem] justify-center items-center">
          {item.action}
        </span>
        <span className="flex min-w-[4.1rem] justify-center items-center">
          {item.collaboration}
        </span>
        <span className="flex min-w-[4.1rem] justify-center items-center">
          {item.focus}
        </span>
        <span className="flex min-w-[4.1rem] justify-center items-center">
          {item.eager}
        </span>
      </div>
    </div>
  );
};

export default InterviewCommentCellComponent;
