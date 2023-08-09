import { CURRENT_GENERATION } from "@/constants";
import { redirect } from "next/navigation";

const ApplicationPage = () => redirect(`/application/${CURRENT_GENERATION}`);

export default ApplicationPage;
