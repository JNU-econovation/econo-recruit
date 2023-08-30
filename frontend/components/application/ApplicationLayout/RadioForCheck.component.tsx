import Txt from "@/components/common/Txt.component";
import {
  ApplicationQuestion,
  ApplicationRadioForCheck,
} from "@/src/constants/application/type";
import { useLocalStorage } from "@/src/hooks/useLocalstorage.hook";
import classNames from "classnames";
import { FC } from "react";

interface RadioCellProps {
  applicationQuestion: ApplicationQuestion;
  radioForCheckData: ApplicationRadioForCheck;
}

const RadioCell: FC<RadioCellProps> = ({
  applicationQuestion,
  radioForCheckData,
}) => {
  const [radio, setRadio] = useLocalStorage<string>(
    radioForCheckData.name,
    "동의하지 않습니다."
  );
  return (
    <div className="flex-1">
      <Txt typography="h6" className="mb-4 flex-1 block">
        {radioForCheckData.title}
      </Txt>
      <input
        className="mr-2"
        type="radio"
        name={radioForCheckData.name}
        id={radioForCheckData.name + radioForCheckData.value[0]}
        checked={radio === radioForCheckData.value[0]}
        value={radioForCheckData.value[0]}
        onChange={(e) => {
          setRadio(e.target.value);
        }}
      />
      <label
        className="mr-4"
        htmlFor={radioForCheckData.name + radioForCheckData.value[0]}
      >
        {radioForCheckData.value[0]}
      </label>
      <input
        className="mr-2"
        type="radio"
        name={radioForCheckData.name}
        id={radioForCheckData.name + radioForCheckData.value[1]}
        checked={radio === radioForCheckData.value[1]}
        value={radioForCheckData.value[1]}
        onChange={(e) => {
          setRadio(e.target.value);
        }}
      />
      <label
        className="mr-4"
        htmlFor={radioForCheckData.name + radioForCheckData.value[1]}
      >
        {radioForCheckData.value[1]}
      </label>
    </div>
  );
};

interface ApplicationRadioForCheckProps {
  applicationQuestion: ApplicationQuestion;
}

const ApplicationRadioForCheckLayout: FC<ApplicationRadioForCheckProps> = ({
  applicationQuestion,
}) => {
  return (
    <div className={classNames(applicationQuestion.id !== -1 ? "pr-12" : "")}>
      {applicationQuestion.id !== -1 && applicationQuestion.title && (
        <>
          <Txt typography="h6">{`${applicationQuestion.id}. `}</Txt>
          <Txt typography="h6" className="break-keep">{`${
            applicationQuestion.title
          }${applicationQuestion.require ? "*" : ""}`}</Txt>
        </>
      )}
      <div className="flex py-8 pl-12">
        {applicationQuestion.nodes.map((node, index) => {
          const radioForCheckData = node as ApplicationRadioForCheck;
          return (
            <RadioCell
              applicationQuestion={applicationQuestion}
              radioForCheckData={radioForCheckData}
              key={index}
            />
          );
        })}
      </div>
    </div>
  );
};

export default ApplicationRadioForCheckLayout;
