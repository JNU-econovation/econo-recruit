import { nanoid } from 'nanoid';

type commonRadioType = {
  title: string;
  name: string;
  checked: boolean;
  value: string;
  onChange: () => void;
  disabled?: boolean;
};

const RadioButtonComponent = ({
  title,
  name,
  checked,
  value,
  onChange,
  disabled = false,
}: commonRadioType) => {
  const checkedClassName = checked ? 'bg-[#303030] text-white' : '';
  const disabledClassName = disabled
    ? 'bg-[#F2F2F2] text-[#B9B9B9] cursor-default'
    : '';
  const uniqueId = nanoid();

  return (
    <>
      <input
        className={`hidden`}
        type="radio"
        name={name}
        id={uniqueId}
        value={value}
        checked={checked}
        onChange={onChange}
        disabled={disabled}
      />
      <label
        className={
          `border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer ` +
          checkedClassName +
          disabledClassName
        }
        htmlFor={uniqueId}
      >
        {title}
      </label>
    </>
  );
};

export default RadioButtonComponent;
