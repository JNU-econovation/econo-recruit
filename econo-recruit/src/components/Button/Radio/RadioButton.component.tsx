import { SetStateAction } from "jotai";

type SetAtom<Args extends any[], Result> = (...args: Args) => Result;

type applicationRadioType = {
    title: string;
    id: string;
    onChange?: () => void;
    setIsNone?: SetAtom<[SetStateAction<boolean>], void>; 
  };
  
  const RadioButtonComponent = ({ title, id }: applicationRadioType) => {
    return (
      <>
        <input type="radio" id={id} value={title} />
        <label htmlFor={id}>{title}</label>
      </>
    );
  };
  
  export default RadioButtonComponent;
  