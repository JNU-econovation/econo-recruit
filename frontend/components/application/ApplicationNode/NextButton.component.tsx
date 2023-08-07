"use client";

import useApplicationIndexControll from "@/hooks/useApplicationIndexControll.hook";
import { FC } from "react";

interface ApplicationNextButtonProps {
  canNext: boolean;
  applicationLength: number;
  beforeCheckCallback?: () => boolean;
}

const ApplicationNextButton: FC<ApplicationNextButtonProps> = ({
  canNext,
  beforeCheckCallback,
  applicationLength,
}) => {
  const { applicationIndex, goNextIndex, goPrevIndex } =
    useApplicationIndexControll();
  const nextButtonClassName =
    "flex-1 rounded-md flex justify-center items-center p-4";

  return (
    <div className="flex gap-2 mt-4">
      <button
        className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
        onClick={goPrevIndex}
      >
        이전
      </button>
      <button
        onClick={
          beforeCheckCallback
            ? () => {
                if (beforeCheckCallback()) goNextIndex();
              }
            : goNextIndex
        }
        disabled={!canNext}
        className={
          canNext
            ? nextButtonClassName + " bg-[#303030] text-white"
            : nextButtonClassName + " bg-[#EFEFEF] text-[#C8C8C8]"
        }
      >
        {`다음(${applicationIndex + 1}/${applicationLength})`}
      </button>
    </div>
  );
};

export default ApplicationNextButton;
