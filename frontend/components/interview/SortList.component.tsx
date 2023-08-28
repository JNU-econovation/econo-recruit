"use client";
import SortListComponent from "../common/SortList.component";
import { useSearchParams } from "next/navigation";

const InterviewSortList = () => {
  const searchParmas = useSearchParams();
  const orderMenu = [
    { type: "newest", string: "최신순" },
    { type: "name", string: "이름순" },
    { type: "objective", string: "지원분야순" },
    { type: "score", string: "점수순" },
  ];
  const order = searchParmas.get("order") ?? "newest";

  return <SortListComponent sortList={orderMenu} selected={order} />;
};

export default InterviewSortList;
