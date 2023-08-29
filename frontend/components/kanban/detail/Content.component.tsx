import ApplicantDetailLeft from "@/components/applicant/DetailLeft.component";
import ApplicantDetailRight from "@/components/applicant/DetailRight.component";
import { getApplicant } from "@/src/apis/applicant/applicant";
import { FC } from "react";

interface KanbanDetailContentProps {
  detailId: string;
  generation: string;
}

const KanbanDetailContent: FC<KanbanDetailContentProps> = async ({
  detailId,
  generation,
}) => {
  const data = await getApplicant(detailId);

  return (
    <div className="flex flex-col gap-12 ">
      <div className="ml-24 text-3xl font-bold pb-4 border-b-4">
        {generation}기 신입모집
      </div>
      <div className="">
        <div className="flex flex-1 pl-12 h-[calc(100vh-12rem)]">
          <div className="flex flex-1 min-h-0">
            <div className="flex-1 overflow-auto px-12 min-w-[35rem]">
              <ApplicantDetailLeft data={data} postId={"22"} />
            </div>
          </div>
          <div className="flex flex-1 min-h-0 [box-shadow:0px_0px_6px_1px_rgba(0,0,0,0.14)] mr-12">
            <div className="flex-1 overflow-auto p-12 min-w-[40rem]">
              <ApplicantDetailRight data={data} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default KanbanDetailContent;
