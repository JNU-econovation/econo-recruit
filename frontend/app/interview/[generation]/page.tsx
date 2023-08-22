"use client";
import PageNavbarComponent from "@/components/common/PageNavbar.component";
import SortListComponent from "@/components/common/SortList.component";
import CommonNavbar from "@/components/common/navbar/Navbar.component";
import InterviewListComponent from "@/components/interview/List.component";
import InterviewPopupComponent from "@/components/interview/Popup.component";
import InterviewSearchComponent from "@/components/interview/Search.component";
import { InterviewListMock } from "@/mock/MockData";
import { interviewListState } from "@/src/stores/interview/Interview.atom";
import { useSetAtom } from "jotai";
import { useSearchParams } from "next/navigation";
import { FC, useEffect } from "react";

interface InterviewPageProps {
  params: {
    generation: string;
  };
}

const InterviewPage: FC<InterviewPageProps> = ({ params }) => {
  const { generation } = params;
  const searchParmas = useSearchParams();
  const type = searchParmas.get("type") ?? "list";
  const order = searchParmas.get("order") ?? "newest";
  const page = searchParmas.get("page") ?? "1";
  const setInterviewList = useSetAtom(interviewListState);

  const orderMenu = [
    { type: "newest", string: "최신순" },
    { type: "name", string: "이름순" },
    { type: "objective", string: "지원분야순" },
    { type: "score", string: "점수순" },
  ];

  useEffect(() => {
    setInterviewList(InterviewListMock);
  });

  return (
    <>
      <InterviewPopupComponent />
      <div className="px-24 w-max-[1280px] flex p-12">
        <CommonNavbar generation={generation} />
        <div className="flex-1 ml-32 min-w-[46rem] mb-48">
          <div className="flex w-full justify-end gap-8 my-12">
            <InterviewSearchComponent />
            <SortListComponent sortList={orderMenu} selected={order} />
          </div>
          <InterviewListComponent />
          <PageNavbarComponent
            maxLength={4}
            page={+page}
            url={`/interview/${generation}?type=${type}&order=${order}`}
          />
        </div>
      </div>
    </>
  );
};

export default InterviewPage;
