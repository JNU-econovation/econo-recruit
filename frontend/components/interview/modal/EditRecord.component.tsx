import LabeledInput from "@/components/common/LabeledInput.component";
import LabeledTextarea from "@/components/common/LabeledTextarea";
import {
  InterviewRes,
  interviewReqBody,
  postInterviewRecord,
  putInterviewRecord,
  putInterviewUrl,
} from "@/src/apis/interview/record";
import { useMutation } from "@tanstack/react-query";
import { useState } from "react";

type InterviewEditRecordComponentProps = {
  applicantId: string;
  data: InterviewRes;
};

const InterviewEditRecordComponent = ({
  applicantId,
  data,
}: InterviewEditRecordComponentProps) => {
  const isDataExist = !!data.url && !!data.record;
  const [isOpen, setIsOpen] = useState(false);
  const [interviewData, setInterviewData] = useState({
    applicantId: applicantId,
    url: data.url || "",
    record: data.record || "",
  } as interviewReqBody);

  const { mutate: interviewUpload } = useMutation(postInterviewRecord);
  const { mutate: editUrl } = useMutation(putInterviewUrl);
  const { mutate: editRecord } = useMutation(putInterviewRecord);

  const handleUpload = () => {
    setIsOpen(false);
    if (isDataExist) {
      if (interviewData.url !== data.url) editUrl(interviewData);
      if (interviewData.record !== data.record) editRecord(interviewData);
    } else {
      interviewUpload(interviewData);
    }
  };

  return (
    <div className="flex flex-col w-full my-10 items-end">
      <button onClick={() => setIsOpen((prev) => !prev)}>
        <span className="text-sm text-[#A7A7A7] underline underline-offset-2">
          {isOpen ? "접어 두기" : "면접기록 수정하기"}
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
              onClick={handleUpload}
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

export default InterviewEditRecordComponent;
