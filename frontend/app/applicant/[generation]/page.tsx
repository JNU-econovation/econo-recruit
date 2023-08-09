import ApplicationQuestion from "@/components/application/ApplicationQuestion.component";
import CommonNavbar from "@/components/common/Navbar.component";
import { FC } from "react";

interface ApplicantPageProps {
  params: {
    generation: string;
  };
}

const ApplicantPage: FC<ApplicantPageProps> = ({ params }) => {
  const { generation } = params;
  const applicationQuestions =
    require(`@/constants/application/${generation}.ts`)
      .APPLICATION as ApplicationQuestion[];

  return (
    <article className="flex gap-24 mt-24 min-w-[1280px]">
      <div className="flex-1 pl-12">
        <CommonNavbar generation={generation} />
      </div>
      <section className="flex-[3_0_0] flex gap-24 mt-24 min-w-[1600px]"></section>
    </article>
  );
};

export default ApplicantPage;
