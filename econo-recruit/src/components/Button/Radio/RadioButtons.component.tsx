import RadioButtonComponent from './RadioButton.component';

type RadioButtonsComponent = {
  radioButtons: { title: string; value: string }[];
  radioSelectedStore: [string, (value: string) => void];
  groupName: string;
  disabledValue?: string;
};

const RadioButtonsComponent = ({
  radioButtons,
  radioSelectedStore,
  groupName,
  disabledValue,
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
          onChange={() => setRadioSelected(data.value)}
        />
      ))}
    </>
  );
};

export default RadioButtonsComponent;
