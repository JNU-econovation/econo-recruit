import ApplicationNavbar from "@/src/components/application/ApplicationNavbar.component";
import { FC } from "react";

interface ApplicationPageProps {
  params: {
    period: string;
  };
}

const ApplicationPage: FC<ApplicationPageProps> = ({ params }) => {
  const { period } = params;

  return (
    <div>
      <ApplicationNavbar />
    </div>
  );
};

export default ApplicationPage;
