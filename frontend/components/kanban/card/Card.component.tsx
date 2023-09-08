import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import { useParams, useRouter } from "next/navigation";

type KanbanCardComponentType = {
  data: KanbanCardData | null;
  cardId: number;
};

function KanbanCardComponent({ data, cardId }: KanbanCardComponentType) {
  const { generation } = useParams();
  const navigate = useRouter();

  if (!data) {
    return <></>;
  }

  const {
    title,
    apply,
    comment,
    isHearted,
    heart,
    major,
    applicantId,
    cardType,
  } = data;

  const onClickDetail = () => {
    navigate.push(
      `/kanban/${generation}/detail?id=${applicantId}&card=${cardId}&type=${cardType}`
    );
  };

  return (
    <div
      className="border-[1px] border-[#F0F0F0] w-full p-3 rounded-lg drop-shadow-md bg-white hover:border-[#7AA0FF]"
      onClick={onClickDetail}
    >
      <div className="text-xs text-[#666666]">{major}</div>
      <div className="font-bold">{title}</div>
      <div className="mt-2 flex justify-between items-center text-sm text-[#666666]">
        <div className="text-sm">{apply.join(" / ")}</div>
        <div className="flex gap-3">
          <div className="flex">
            <img src="/icons/bubble.right.svg" alt="comment" />
            {comment}
          </div>
          <div className="flex">
            {isHearted ? (
              <img src="/icons/heart.point.svg" alt="heart" />
            ) : (
              <img src="/icons/heart.svg" alt="heart" />
            )}
            {heart}
          </div>
        </div>
      </div>
    </div>
  );
}

export default KanbanCardComponent;
