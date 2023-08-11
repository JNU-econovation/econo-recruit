import ApplicantBoard from "@/components/applicant/Board.component";
import CommonNavbar from "@/components/common/navbar/Navbar.component";
import { FC } from "react";

interface ApplicantPageProps {
  params: {
    generation: string;
  };
}

const ApplicantPage: FC<ApplicantPageProps> = ({ params }) => {
  const { generation } = params;
  return (
    <div className="px-24 w-max-[1280px] flex p-12">
      <CommonNavbar generation={generation} />
      <div className="flex-1 ml-32 min-w-[46rem]">
        <ApplicantBoard />
      </div>
    </div>
  );
};

export default ApplicantPage;
