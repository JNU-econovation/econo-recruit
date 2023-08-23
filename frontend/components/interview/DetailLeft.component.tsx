import InterviewCommentComponent from "./Comment.component";
import InterviewLabelComponent from "./Label.component";
import InterviewUserComponent from "./User.component";

const InterviewDetailLeftComponent = (props: any) => {
  return (
    <>
      <InterviewUserComponent src="." />
      <InterviewLabelComponent />
      <InterviewCommentComponent />
    </>
  );
};

export default InterviewDetailLeftComponent;
