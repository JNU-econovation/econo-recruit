const ApplicantUserComponent = () => {
  return (
    <div className="w-full">
      <div className="text-lg text-gray-600">소프트웨어공학과</div>
      <div className="text-2xl font-bold my-2">[개발자] 임채승</div>
      <button className="text-sm underline underline-offset-2">
        면접관 접수 입력
      </button>
      <div className="flex gap-4 my-6">
        <div className="text-lg text-gray-600">
          1지망: <span className="font-bold text-[#2160FF]">APP</span>
        </div>
        <div className="text-lg text-gray-600">
          2지망: <span className="font-bold text-[#2160FF]">WEB</span>
        </div>
      </div>
      <div className="my-12">
        <div className="text-lg my-2 font-semibold">포트폴리오</div>
        <div className="text-sm flex gap-6">
          <div>
            <div>링크</div>
            <a href="#" className="underline underline-offset-2">
              https://econovation.kr/about
            </a>
          </div>
          <div>
            <div>파일</div>
            <a href="#" className="underline underline-offset-2">
              https://econovation.kr/about
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};
export default ApplicantUserComponent;
