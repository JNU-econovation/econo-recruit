type CommentDetailType = {
  comment: {
    id: number;
    author: string;
    date: string;
    content: string;
    like: number;
    checked: boolean;
  };
};

const CommentDetailComponent = ({ comment }: CommentDetailType) => {
  const { id, author, date, content, like, checked } = comment;
  return (
    <div className="border-l-4 border-[#717171] pl-3">
      <div className="flex justify-between mb-4">
        <div className="flex gap-4 items-end">
          <div>{author}</div>
          <div className="text-xs">{date}</div>
        </div>
        <div className="flex gap-2 items-end">
          <img
            src={checked ? '/face.smiling.fill.svg' : '/face.smiling.svg'}
            alt=""
          />
          <span className="text-xs text-[#808080]">{like}</span>
        </div>
      </div>
      <div className="text-sm">{content}</div>
      {id === 3 ? (
        <div className="flex text-sm gap-2 text-[#666666] items-center">
          <button>수정</button>
          <div className="border-x-[0.5px] h-4 !w-0 border-[#666666]"></div>
          <button>삭제</button>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default CommentDetailComponent;
