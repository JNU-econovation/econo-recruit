"use client";

import {
  ApplicantLabelReq,
  getApplicantLabel,
  postApplicantLabel,
} from "@/src/apis/applicant/applicant";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import classNames from "classnames";
import { FC, useState } from "react";

interface ApplicantLabelProps {
  postId: string;
  generation: string;
}

const ApplicantLabel: FC<ApplicantLabelProps> = ({ postId, generation }) => {
  const [openAdditional, setOpenAdditional] = useState(false);

  const { data, error, isLoading } = useQuery<ApplicantLabelReq[]>(
    ["applicantLabel", postId],
    () => getApplicantLabel(postId),
    {
      enabled: !!postId,
    }
  );

  if (!data || isLoading) {
    return <div>로딩중...</div>;
  }

  if (error) {
    return <div>에러 발생</div>;
  }

  const toggleOpen = () => {
    setOpenAdditional((prev) => !prev);
  };

  return (
    <div className="my-12">
      <div className="text-lg font-semibold">
        라벨링
        <span className="text-base font-normal ml-2">
          {data.filter((label) => label.active).length}개
        </span>
      </div>
      <div className="flex items-baseline gap-2">
        <div className="grid grid-cols-6 gap-2 my-4 w-fit">
          {openAdditional
            ? data.map((label) => (
                <ApplicantLabelButton
                  generation={generation}
                  key={label.name}
                  label={label}
                  postId={postId}
                />
              ))
            : data
                .map((label) => (
                  <ApplicantLabelButton
                    generation={generation}
                    key={label.name}
                    label={label}
                    postId={postId}
                  />
                ))
                .slice(0, 6)}
        </div>
        <button
          onClick={toggleOpen}
          className={classNames(
            "text-[#2160FF] bg-[#E8EFFF] translate-y-[2px] h-8 w-8 rounded-full flex items-center justify-center transition-all",
            { "rotate-45 ": openAdditional }
          )}
        >
          <img src="/icons/ellipsis.plus.blue.svg" alt="plus" />
        </button>
      </div>
    </div>
  );
};

interface ApplicantLabelButtonProps {
  label: ApplicantLabelReq;
  postId: string;
  generation: string;
}

const ApplicantLabelButton: FC<ApplicantLabelButtonProps> = ({
  label,
  postId,
  generation,
}) => {
  const queryClient = useQueryClient();

  const { mutate } = useMutation(postApplicantLabel, {
    onSettled: () => {
      queryClient.invalidateQueries({ queryKey: ["applicantLabel", postId] });
      queryClient.invalidateQueries({
        queryKey: ["kanbanDataArray", generation],
      });
    },
  });

  const onLabelClick = () => {
    mutate(postId);
  };

  return (
    <button
      key={label.name}
      className={classNames(
        "py-1 px-4 rounded-full w-max",
        label.active
          ? "text-[#2160FF] bg-[#E8EFFF]"
          : "text-[#777777] bg-[#EFEFEF]"
      )}
      onClick={onLabelClick}
    >
      {label.name}
    </button>
  );
};

export default ApplicantLabel;