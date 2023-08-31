import LabeledInput from "@/components/common/LabeledInput.component";
import LabeledTextarea from "@/components/common/LabeledTextarea";
import {
  interviewReqBody,
  postInterviewRecord,
} from "@/src/apis/interview/interview";
import { useMutation } from "@tanstack/react-query";
import { useState } from "react";

const InterviewUploadComponent = ({ applicantId }: { applicantId: string }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [interviewData, setInterviewData] = useState({
    applicantId: applicantId,
    url: "",
    record: "",
  } as interviewReqBody);

  const { mutate: interviewUpload } = useMutation(postInterviewRecord);

  return (
    <div className="flex flex-col w-full my-10 items-end">
      <button onClick={() => setIsOpen((prev) => !prev)}>
        <span className="text-sm text-[#A7A7A7] underline underline-offset-2">
          {isOpen ? "접어 두기" : "면접기록 입력하기"}
        </span>
      </button>
      {isOpen ? (
        <div className="flex flex-col w-full gap-2 mt-4">
          <LabeledInput
            label="동영상 URL"
            id="interviewUrl"
            value={interviewData.url}
            onChange={(e) => {
              setInterviewData({
                ...interviewData,
                url: e.target.value,
              });
            }}
            placeholder="URL을 입력해주세요."
          />
          <LabeledTextarea
            label="면접 기록"
            id="interviewRecord"
            value={interviewData.record}
            onChange={(e) => {
              setInterviewData({
                ...interviewData,
                record: e.target.value,
              });
            }}
            placeholder="면접 기록을 입력해주세요."
          />
          <div className="flex gap-2 mt-4">
            <button
              className="flex-1 rounded-md flex justify-center items-center p-3 bg-[#EFEFEF]"
              onClick={() => {
                setIsOpen(false);
                setInterviewData({
                  applicantId: applicantId,
                  url: "",
                  record: "",
                });
              }}
            >
              취소
            </button>
            <button
              className="flex-1 rounded-md flex justify-center items-center p-3 bg-[#303030] text-white"
              onClick={() => {
                setIsOpen(false);
                interviewUpload(interviewData);
              }}
            >
              제출하기
            </button>
          </div>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
};

export default InterviewUploadComponent;