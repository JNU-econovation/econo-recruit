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
    <div className="flex gap-[5.5rem] items-center">
      <div className="text-[#2160FF] bg-[#E8EFFF] px-5 py-2 rounded-3xl">
        {item.name}
      </div>
      <div className="flex text-xl gap-[5rem]">
        <span>{item.passion}</span>
        <span>{item.action}</span>
        <span>{item.collaboration}</span>
        <span>{item.focus}</span>
        <span>{item.eager}</span>
      </div>
    </div>
  );
};

export default InterviewCommentCellComponent;
