import RadioButtonComponent from './RadioButton.component';

type RadioButtonsComponent = {
  radioButtons: { title: string; value: string }[];
  radioSelectedStore: [string, (value: string) => void];
  groupName: string;
  disabledValue?: string;
  onChangeCallback?: () => void;
};

const RadioButtonsComponent = ({
  radioButtons,
  radioSelectedStore,
  groupName,
  disabledValue,
  onChangeCallback = () => {},
}: RadioButtonsComponent) => {
  const [radioSelected, setRadioSelected] = radioSelectedStore;

  return (
    <>
      {radioButtons.map((data, index) => (
        <RadioButtonComponent
          key={index}
          checked={data.value === radioSelected}
          name={groupName}
          title={data.title}
          value={data.value}
          disabled={disabledValue === data.value}
          onChange={() => {
            setRadioSelected(data.value);
            onChangeCallback();
          }}
        />
      ))}
    </>
  );
};

export default RadioButtonsComponent;
