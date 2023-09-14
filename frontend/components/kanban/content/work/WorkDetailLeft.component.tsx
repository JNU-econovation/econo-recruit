import { FC } from "react";
import { applicantDataFinder } from "@/src/functions/finder";
import ApplicantLabel from "@/components/applicant/applicantNode/Label.component";
import { Work } from "@/src/apis/work/work";
import dynamic from "next/dynamic";
import Txt from "@/components/common/Txt.component";
import WorkLabel from "./Label.component";

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
  return (
    <>
      <div className="flex flex-col gap-1 mb-8">
        <Txt typography="h2">{data.title}</Txt>
      </div>
      <WorkLabel cardId={cardId} generation={generation} />
      <ApplicantComment cardId={cardId} postId="" generation={generation} />
    </>
  );
};

export default WorkDetailLeft;
