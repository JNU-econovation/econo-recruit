type InterviewUserComponent = {
  src: string;
};

const InterviewUserComponent = ({ src }: InterviewUserComponent) => {
  return (
    <div className="w-full h-[24rem]">
      <video src={src} className="w-full h-full bg-[#D9D9D9]" />
    </div>
  );
};
export default InterviewUserComponent;
