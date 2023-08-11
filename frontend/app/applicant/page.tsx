import { CURRENT_GENERATION } from "@/constants";
import { redirect } from "next/navigation";

const ApplicantPage = () => redirect(`/applicant/${CURRENT_GENERATION}`);

export default ApplicantPage;
