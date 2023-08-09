import ApplicationNavbar from "@/components/application/ApplicationNavbar.component";
import ApplicationQuestion from "@/components/application/ApplicationQuestion.component";
import { FC } from "react";

interface ApplicationPageProps {
  params: {
    generation: string;
  };
}

const ApplicationPage: FC<ApplicationPageProps> = ({ params }) => {
  const { generation } = params;
  const applicationQuestions =
    require(`@/constants/application/${generation}.ts`)
      .APPLICATION as ApplicationQuestion[];

  return (
    <section className="flex gap-24 mt-24 min-w-[1280px]">
      <ApplicationNavbar
        className="flex-1"
        applicationQuestions={applicationQuestions}
      />
      <ApplicationQuestion
        className="flex-[3_0_0]"
        applicationQuestions={applicationQuestions}
      />
    </section>
  );
};

export default ApplicationPage;
