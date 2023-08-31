"use client";
import PageNavbarComponent from "../common/PageNavbar.component";
import { useSearchParams } from "next/navigation";

type ApplicantPageNavbarProps = {
  generation: string;
};

const ApplicantPageNavbar = ({ generation }: ApplicantPageNavbarProps) => {
  const searchParmas = useSearchParams();
  const type = searchParmas.get("type") ?? "list";
  const order = searchParmas.get("order") ?? "newest";
  const page = searchParmas.get("page") ?? "1";
  return (
    <PageNavbarComponent
      maxLength={4}
      page={+page}
      url={`/applicant/${generation}?type=${type}&order=${order}`}
    />
  );
};

export default ApplicantPageNavbar;
