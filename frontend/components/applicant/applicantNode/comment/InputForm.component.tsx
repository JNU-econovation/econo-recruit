"use client";

import { FC, FormEvent, use, useCallback, useEffect, useState } from "react";
import React from "react";
import { Editor } from "@toast-ui/react-editor";

import "@toast-ui/editor/dist/toastui-editor.css";
import { useMutation } from "@tanstack/react-query";
import { postComment } from "@/src/apis/comment/comment";

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
  applicantId: string;
}

const ApplicantCommentInputForm: FC<ApplicantCommentInputFormProps> = ({
  applicantId,
}) => {
  const [isNocomment, setIsNocomment] = useState(false);
  const [hasQuestion, setHasQuestion] = useState(false);
  const [content, setContent] = useState("");
  const editorRef = React.useRef<Editor>(null);

  const { mutate } = useMutation(
    () => {
      return postComment({
        content,
        applicantId,
        cardId: 0,
        parentCommentId: 0,
      });
    },
    { onSettled: () => {} }
  );

  const onNocommentCheck = useCallback(() => {
    setIsNocomment(!isNocomment);
    if (editorRef.current) {
      editorRef.current
        .getInstance()
        .setMarkdown(isNocomment ? "" : "지인이므로 코멘트 삼가겠습니다.");
    }
  }, [isNocomment]);

  const prevSubmit = () => {
    const content = editorRef.current?.getInstance().getMarkdown();
    setContent((hasQuestion ? "**[질문]**" : "") + content);
  };

  useEffect(() => {
    if (editorRef.current) {
      document.querySelector(".toastui-editor-toolbar")?.remove();
      document.querySelector(".toastui-editor-mode-switch")?.remove();
    }
  }, []);

  return (
    <form
      onSubmit={(e) => {
        prevSubmit();
        mutate();
      }}
    >
      <Editor
        className="w-full my-4 border-[1px] rounded border-[#DBDBDB] p-3 text-sm"
        height="6rem"
        initialEditType="markdown"
        usageStatistics={false}
        language="ko-KR"
        onChange={() => {
          isNocomment &&
            editorRef.current?.getInstance().getMarkdown() !==
              "지인이므로 코멘트 삼가겠습니다." &&
            editorRef.current
              ?.getInstance()
              .setMarkdown("지인이므로 코멘트 삼가겠습니다.");
        }}
        ref={editorRef}
      />
      <textarea className="hidden"></textarea>
      <div className="font-normal">
        <InputCheckBox
          name="question"
          id="question"
          title="질문드립니다."
          onChange={() => setHasQuestion((prev) => !prev)}
        />
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