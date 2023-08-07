"use client";

import classNames from "classnames";
import { ChangeEvent, FC, useId } from "react";

interface RadioProps {
  label: string;
  name: string;
  value: string;
  disabled?: boolean;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  isCheck: boolean;
  isLast: boolean;
  onClick?: Function;
}

const Radio: FC<RadioProps> = ({
  label,
  name,
  value,
  onChange,
  isCheck,
  disabled,
  isLast,
  onClick,
}) => {
  const id = useId();
  return (
    <div className={classNames(isLast && "col-start-1 col-end-[-1]")}>
      <label
        htmlFor={id}
        className={classNames(
          "flex items-center justify-center w-full py-4 border rounded-md cursor-pointer",
          isCheck
            ? "bg-[#303030] text-white border-black"
            : "border-gray-300 text-black bg-white",
          disabled && "bg-gray-200 text-gray-400 cursor-not-allowed"
        )}
        onClick={() => {
          if (typeof onClick === "function") onClick();
        }}
      >
        {label}
      </label>
      <input
        type="radio"
        id={id}
        name={name}
        checked={isCheck}
        value={value}
        onChange={onChange}
        className="hidden"
        disabled={disabled}
      />
    </div>
  );
};

interface RadioGroupProps {
  name: string;
  value: string;
  disableValue?: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  radioList: string[];
  splitNumber?: number;
  isSpaned?: boolean;
  onClick?: Function;
}

const RadioGroup: FC<RadioGroupProps> = ({
  name,
  value,
  onChange,
  radioList,
  disableValue,
  splitNumber = 2,
  isSpaned = false,
  onClick,
}) => (
  <div
    className={`grid gap-2 grid-cols-${splitNumber} col-end-auto font-semibold`}
  >
    {radioList.map((radioData, index) => (
      <Radio
        key={index}
        label={radioData}
        name={name}
        value={radioData}
        onChange={onChange}
        disabled={disableValue === radioData}
        isCheck={radioData === value}
        isLast={
          isSpaned &&
          index % splitNumber === 0 &&
          index === radioList.length - 1
        }
        onClick={onClick}
      />
    ))}
  </div>
);
export default RadioGroup;
