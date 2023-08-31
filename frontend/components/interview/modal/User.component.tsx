type InterviewUserComponent = {
  src: string;
};

const InterviewUserComponent = ({ src }: InterviewUserComponent) => {
  return (
    <div className="w-full pb-[56.25%] relative h-0">
      {!src ? (
        <div className="w-full h-full absolute top-0 left-0 bg-gray-200"></div>
      ) : (
        <iframe
          src={src}
          title="김수민님.mp4"
          className="w-full h-full absolute top-0 left-0"
        />
      )}
    </div>
  );
};
export default InterviewUserComponent;
