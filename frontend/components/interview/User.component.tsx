type InterviewUserComponent = {
  src: string;
};

const InterviewUserComponent = ({ src }: InterviewUserComponent) => {
  return (
    <div className="w-full aspect-video">
      <iframe src={src} width="560" height="315" title="김수민님.mp4"></iframe>
    </div>
  );
};
export default InterviewUserComponent;
