type InputTextComponent = {
  onError: boolean;
  placeholder: string;
  value: string;
  onChangeText: (e: string) => void;
  maxTextLength?: number;
};

const InputTextComponent = ({
  onError,
  placeholder,
  value,
  onChangeText,
  maxTextLength,
}: InputTextComponent) => {
  const inputTextClassName =
    'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md';

  return (
    <input
      type="text"
      placeholder={placeholder}
      value={value}
      onChange={(e) => onChangeText(e.target.value)}
      className={
        onError ? 'border-[#DC0000] ' + inputTextClassName : inputTextClassName
      }
      maxLength={maxTextLength ? maxTextLength : undefined}
    />
  );
};

export default InputTextComponent;
