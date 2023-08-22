import { redirect } from "next/navigation";
import { CURRENT_GENERATION } from "@/src/constants";

const searchParams = new URLSearchParams(location.search);
const InterviewPage = () =>
  redirect(`/interview/${CURRENT_GENERATION}?${searchParams.toString()}`);

export default InterviewPage;
