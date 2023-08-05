import ApplicationNavbar from "@/components/application/ApplicationNavbar.component";
import { FC } from "react";

interface ApplicationPageProps {
  params: {
    generation: string;
  };
}

const ApplicationPage: FC<ApplicationPageProps> = ({ params }) => {
  const { generation } = params;

  return (
    <div>
      <ApplicationNavbar generation={generation} />
    </div>
  );
};

export default ApplicationPage;
