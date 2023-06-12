import React from "react";

type InputCheckBoxProps = {
  name: string;
  id: string;
  title: string;
  checked?: boolean;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputCheckBox = ({ name, id, title, checked, onChange }: InputCheckBoxProps) => {
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
  )
}

export default React.memo(InputCheckBox);