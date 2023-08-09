import { CURRENT_GENERATION } from "@/constants";
import { redirect } from "next/navigation";

const ApplicantPage = () => redirect(`/application/${CURRENT_GENERATION}`);

export default ApplicantPage;
