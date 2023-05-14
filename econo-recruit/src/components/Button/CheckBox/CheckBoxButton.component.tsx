import { nanoid } from 'nanoid';

type commonCheckBoxType = {
  title: string;
  name: string;
  checked: boolean;
  value: string;
  onChange: () => void;
  disabled?: boolean;
  onClick?: () => void;
};

const CheckBoxButtonComponent = ({
  title,
  name,
  checked,
  value,
  onChange,
  onClick,
}: commonCheckBoxType) => {
  const checkedClassName = checked ? 'bg-[#303030] text-white' : '';
  const uniqueId = nanoid();

  return (
    <>
      <input
        className={`hidden`}
        type="checkbox"
        name={name}
        id={uniqueId}
        value={value}
        onChange={onChange}
        onClick={onClick}
      />
      <label
        className={
          `border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer ` +
          checkedClassName
        }
        htmlFor={uniqueId}
      >
        {title}
      </label>
    </>
  );
};

export default CheckBoxButtonComponent;
