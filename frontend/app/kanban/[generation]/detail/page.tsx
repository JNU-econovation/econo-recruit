"use client";

import { FC } from "react";
import KanbanColumnComponent from "@/components/kanban/Column.component";
import { useRouter, useSearchParams } from "next/navigation";
import { useAtomValue } from "jotai";
import { KanbanDataArrayState } from "@/src/stores/kanban/Kanban.atoms";
import ApplicantDetailLeft from "@/components/applicant/DetailLeft.component";
import ApplicantDetailRight from "@/components/applicant/DetailRight.component";
import { useQuery } from "@tanstack/react-query";
import { getApplicant } from "@/src/apis/applicant";

interface KanbanBoardDetailPageProps {
  params: {
    generation: string;
  };
}

const KanbanBoardDetailPage: FC<KanbanBoardDetailPageProps> = ({ params }) => {
  const { generation } = params;
  const navigate = useRouter();
  const searchParams = useSearchParams();

  const detailId = searchParams.get("id") ?? "0";
  const detailRow = searchParams.get("row") ?? "0";

  const kanbanDataArray = useAtomValue(KanbanDataArrayState);
  const rowTitle = kanbanDataArray[+detailRow].title;
  const columnCount = kanbanDataArray[+detailRow].column.length;

  const { data, isLoading, isError } = useQuery(
    ["applicant", detailId],
    () => getApplicant(detailId),
    {
      enabled: !!detailId,
    }
  );

  if (!data || isLoading) {
    return <div>로딩중...</div>;
  }

  if (isError) {
    return <div>에러 발생</div>;
  }

  const onBackClick = () => {
    navigate.back();
  };

  return (
    <div className="pl-24 w-screen h-screen">
      <div className="flex items-center pt-24 pb-6 text-3xl font-bold border-b-4">
        <button onClick={onBackClick} className="-translate-x-12 w-8">
          <img src="/chevron.backward.svg" alt="" />
        </button>
        <div className="-ml-8">{generation}기 신입모집</div>
      </div>
      <div className="flex mt-8 overflow-auto">
        <div className="max-h-[calc(100vh-20rem)] ">
          <div className="flex gap-4">
            <div className="h-fit border-[1px] border-[#F0F0F0] w-fit p-4 rounded-lg min-w-[17rem] bg-white">
              <div className="flex justify-between">
                <div className="flex gap-2 items-center">
                  <div className="font-bold text-lg">{rowTitle}</div>
                  <div className="flex justify-center items-center px-3 rounded-full bg-[#E8EFFF] text-xs text-[#2160FF] h-4">
                    {columnCount}
                  </div>
                </div>
                <button>
                  <img src="/icons/ellipsis.bubble.svg" alt="RowDetail" />
                </button>
              </div>
              <div className="flex flex-col justify-between">
                {kanbanDataArray[+detailRow].column.map((col) => (
                  <div className="my-2">
                    <KanbanColumnComponent data={col} row={+detailRow} />
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
        <div className="flex flex-1 pl-12">
          <div className="flex flex-1 min-h-0">
            <div className="flex-1 overflow-auto px-12">
              <ApplicantDetailLeft data={data} postId={"22"} />
            </div>
          </div>
          <div className="flex flex-1 min-h-0">
            <div className="flex-1 overflow-auto px-12">
              {/* <ApplicantDetailRight data={data} /> */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default KanbanBoardDetailPage;
