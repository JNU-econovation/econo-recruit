"use client";
import PageNavbarComponent from "../common/PageNavbar.component";
import { useSearchParams } from "next/navigation";

type InterviewPageNavbarProps = {
  generation: string;
};

const InterviewPageNavbar = ({ generation }: InterviewPageNavbarProps) => {
  const searchParmas = useSearchParams();
  const type = searchParmas.get("type") ?? "list";
  const order = searchParmas.get("order") ?? "newest";
  const page = searchParmas.get("page") ?? "1";
  return (
    <PageNavbarComponent
      maxLength={4}
      page={+page}
      url={`/interview/${generation}?type=${type}&order=${order}`}
    />
  );
};

export default InterviewPageNavbar;
