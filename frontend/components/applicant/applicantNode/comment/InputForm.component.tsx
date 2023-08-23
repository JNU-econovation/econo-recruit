"use client";

import { FC, FormEvent, useCallback, useState } from "react";
import React from "react";

type InputCheckBoxProps = {
  name: string;
  id: string;
  title: string;
  checked?: boolean;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
};

const InputCheckBox = ({
  name,
  id,
  title,
  checked,
  onChange,
}: InputCheckBoxProps) => {
  return (
    <div className="flex items-center gap-2 text-sm font-normal my-2">
      <input
        className="accent-black"
        type="checkbox"
        name={name}
        id={id}
        checked={checked}
        onChange={onChange}
      />
      <label htmlFor={name}>{title}</label>
    </div>
  );
};

interface ApplicantCommentInputFormProps {
  value: string;
  onChange: (value: string) => void;
  onSubmit: (event: FormEvent) => void;
}

const ApplicantCommentInputForm: FC<ApplicantCommentInputFormProps> = ({
  onChange,
  onSubmit,
  value,
}) => {
  const [isNocomment, setIsNocomment] = useState(false);

  const onNocommentCheck = useCallback(() => {
    setIsNocomment(!isNocomment);
    onChange(isNocomment ? "" : "지인이므로 코멘트 삼가겠습니다.");
  }, [isNocomment]);

  return (
    <form onSubmit={onSubmit}>
      <textarea
        className="w-full my-4 border-[1px] rounded border-[#DBDBDB] p-3 outline-none resize-none text-sm h-24"
        disabled={isNocomment}
        value={value}
        onChange={(e) => onChange(e.target.value)}
      ></textarea>
      <div className="font-normal">
        <InputCheckBox name="question" id="question" title="질문드립니다." />
        <InputCheckBox
          name="nocomment"
          id="nocomment"
          title="지인이므로 코멘트 삼가겠습니다."
          checked={isNocomment}
          onChange={onNocommentCheck}
        />
      </div>
    </form>
  );
};

export default ApplicantCommentInputForm;
