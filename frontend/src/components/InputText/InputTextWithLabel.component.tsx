import InputTextComponent from './InputText.component';

type InputTextWithLabel = {
  title: string;
  onError?: boolean;
  value: string;
  onChangeText: (e: string) => void;
  maxTextLength?: number;
  placeholder?: string;
  subTitle?: string;
};

const inputTextWithLabel = ({
  title,
  onError = false,
  value,
  onChangeText,
  maxTextLength,
  placeholder = '',
  subTitle,
}: InputTextWithLabel) => {
  return (
    <>
      <div className="pb-4">{title}</div>
      <InputTextComponent
        onChangeText={onChangeText}
        onError={onError}
        placeholder={placeholder}
        value={value}
        maxTextLength={maxTextLength ? maxTextLength : undefined}
      />
      <div className="h-4">
        {onError ? (
          <div className="w-full translate-x-[100%]">
            <div className="w-fit text-[#DC0000] -translate-x-[calc(100%+1rem)] -translate-y-10">
              {title.replace(/[*]/g, '').trim()}을 입력해주세요.
            </div>
            {subTitle ? <span className="font-light">{subTitle}</span> : ''}
          </div>
        ) : (
          ''
        )}
      </div>
    </>
  );
};

export default inputTextWithLabel;
