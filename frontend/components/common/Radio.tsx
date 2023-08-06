"use client";

import classNames from "classnames";
import { FC, useId } from "react";

interface RadioProps {
  label: string;
  name: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  isCheck: boolean;
}

const Radio: FC<RadioProps> = ({ label, name, value, onChange, isCheck }) => {
  const id = useId();
  return (
    <>
      <label
        htmlFor={id}
        className={classNames(
          "flex items-center justify-center w-full py-4 border rounded-md cursor-pointer",
          isCheck
            ? "bg-black text-white border-black"
            : "border-gray-300 text-black bg-white"
        )}
      >
        {label}
      </label>
      <input
        type="radio"
        id={id}
        name={name}
        value={value}
        onChange={onChange}
        className="hidden"
      />
    </>
  );
};

interface RadioGroupProps {
  name: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  radioList: string[];
  splitNumber?: number;
}

const RadioGroup: FC<RadioGroupProps> = ({
  name,
  value,
  onChange,
  radioList,
  splitNumber = 2,
}) => {
  return (
    <div className={`grid gap-2 grid-cols-${splitNumber}`}>
      {radioList.map((radioData, index) => (
        <Radio
          key={index}
          label={radioData}
          name={name}
          value={radioData}
          onChange={onChange}
          isCheck={radioData === value}
        />
      ))}
    </div>
  );
};

export default RadioGroup;
