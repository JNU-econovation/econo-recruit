import ApplicationNavbar from "@/components/application/Navbar.component";
import ApplicationQuestion from "@/components/application/Question.component";
import { postApplicant } from "@/src/apis/application";
import { FC } from "react";

interface ApplicationPageProps {
  params: {
    generation: string;
  };
}

const ApplicationPage: FC<ApplicationPageProps> = ({ params }) => {
  const { generation } = params;
  const applicationQuestions =
    require(`@/src/constants/application/${generation}.ts`)
      .APPLICATION as ApplicationQuestion[];

  return (
    <section className="flex gap-24 mt-24 min-w-[1280px]">
      <ApplicationNavbar className="flex-1" generation={generation} />
      <ApplicationQuestion
        className="flex-[3_0_0]"
        applicationQuestions={applicationQuestions}
      />
    </section>
  );
};

export default ApplicationPage;
