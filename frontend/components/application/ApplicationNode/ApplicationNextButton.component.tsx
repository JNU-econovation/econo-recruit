"use client";

import useApplicationPageControll from "@/hooks/useApplicationPageControll.hook";
import { FC } from "react";

interface ApplicationNextButtonProps {
  canNext: boolean;
  beforeCheckCallback?: () => boolean;
}

const ApplicationNextButton: FC<ApplicationNextButtonProps> = ({
  canNext,
  beforeCheckCallback,
}) => {
  const { goNextPage, goPrevPage } = useApplicationPageControll();
  const nextButtonClassName =
    "flex-1 rounded-md flex justify-center items-center p-4";

  return (
    <div className="flex gap-2 mt-4">
      <button
        className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
        onClick={goPrevPage}
      >
        이전
      </button>
      <button
        onClick={
          beforeCheckCallback
            ? () => {
                if (beforeCheckCallback()) goNextPage();
              }
            : goNextPage
        }
        disabled={!canNext}
        className={
          canNext
            ? nextButtonClassName + " bg-[#303030] text-white"
            : nextButtonClassName + " bg-[#EFEFEF] text-[#C8C8C8]"
        }
      >
        다음
      </button>
    </div>
  );
};

export default ApplicationNextButton;
