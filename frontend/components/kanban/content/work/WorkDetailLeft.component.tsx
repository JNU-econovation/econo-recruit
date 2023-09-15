import { FC, use, useEffect, useState } from "react";
import { Work, putWork } from "@/src/apis/work/work";
import dynamic from "next/dynamic";
import Txt from "@/components/common/Txt.component";
import WorkLabel from "./Label.component";
import { useMutation, useQueryClient } from "@tanstack/react-query";

interface WorkDetailLeftProps {
  cardId: number;
  data: Work;
  generation: string;
}

const ApplicantComment = dynamic(
  () =>
    import("@/components/applicant/applicantNode/comment/Comment.component"),
  { ssr: false }
);

const WorkDetailLeft: FC<WorkDetailLeftProps> = ({
  data,
  generation,
  cardId,
}) => {
  const [title, setTitle] = useState("");
  const [isOpenAddCard, setIsOpenAddCard] = useState(false);
  const queryClient = useQueryClient();

  const { mutate: changeWorkCardTitle } = useMutation(putWork, {
    onSettled: () => {
      queryClient.invalidateQueries({
        queryKey: ["work", cardId],
      });
    },
  });

  const addCardSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    changeWorkCardTitle({ cardId, title });
    setIsOpenAddCard(false);
  };

  useEffect(() => {
    setTitle(data.title);
  }, []);

  return (
    <>
      <div className="flex flex-col gap-1 mb-8">
        <div className="flex w-full justify-between items-baseline">
          {isOpenAddCard ? (
            <form className="flex gap-2" onSubmit={(e) => addCardSubmit(e)}>
              <input
                type="text"
                className="font-bold w-full border rounded-lg p-2"
                placeholder="Enter a title for this card"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
              <div className="flex text-sm gap-2 text-[#666666] items-center w-[4rem]">
                <button
                  type="submit"
                  onClick={() => setIsOpenAddCard((prev) => !prev)}
                >
                  수정완료
                </button>
              </div>
            </form>
          ) : (
            <Txt typography="h2">{data.title}</Txt>
          )}
          <div className="flex text-sm gap-2 text-[#666666] items-center">
            <button onClick={() => setIsOpenAddCard((prev) => !prev)}>
              수정
            </button>
          </div>
        </div>
      </div>
      <WorkLabel cardId={cardId} generation={generation} />
      <ApplicantComment cardId={cardId} postId="" generation={generation} />
    </>
  );
};

export default WorkDetailLeft;
