import { getAllInterviewer } from "@/src/apis/interview/interviewer";
import { useQuery } from "@tanstack/react-query";

const AdminBoard = () => {
  const {
    data: userData,
    isLoading,
    isError,
  } = useQuery(["interviewers"], () => getAllInterviewer());

  if (!userData || isLoading) {
    return <div>로딩중...</div>;
  }

  if (isError) {
    return <div>에러 발생</div>;
  }

  return;
};

export default AdminBoard;
