import CommonNavbar from "@/components/common/navbar/Navbar.component";
import InterviewBoardComponent from "@/components/interview/Board.component";
import InterviewPageNavbar from "@/components/interview/PageNavbar.component";
import InterviewSearchComponent from "@/components/interview/Search.component";
import InterviewSortList from "@/components/interview/SortList.component";
import { FC } from "react";

interface InterviewPageProps {
  generation: string;
}

const InterviewPage: FC<InterviewPageProps> = ({ generation }) => {
  return (
    <div className="px-24 max-w-[1280px] flex p-12">
      <CommonNavbar generation={generation} />
      <div className="flex-1 ml-32 min-w-[46rem] mb-48">
        <div className="flex w-full justify-end gap-8 my-12">
          <InterviewSearchComponent />
          <InterviewSortList />
        </div>
        <InterviewBoardComponent />
        <InterviewPageNavbar generation={generation} />
      </div>
    </div>
  );
};

export default InterviewPage;
