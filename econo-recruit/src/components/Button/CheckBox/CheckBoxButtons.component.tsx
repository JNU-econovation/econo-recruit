import CheckBoxButtonComponent from './CheckBoxButton.component';

type CheckBoxButtonsComponent = {
  checkBoxButtons: { title: string; value: string }[];
  checkBoxSelectedStore: [string, (value: string) => void];
  groupName: string;
  onChangeCallback?: () => void;
  onClick?: () => void;
};

const CheckBoxButtonsComponent = ({
  checkBoxButtons,
  checkBoxSelectedStore,
  groupName,
  onChangeCallback = () => {},
  onClick = () => {},
}: CheckBoxButtonsComponent) => {
  const [checkBoxSelected, setCheckBoxSelected] = checkBoxSelectedStore;

  return (
    <>
      {checkBoxButtons.map((data, index) => (
        <CheckBoxButtonComponent
          key={index}
          checked={data.value === checkBoxSelected}
          name={groupName}
          title={data.title}
          value={data.value}
          onChange={() => {
            setCheckBoxSelected(data.value);
            onChangeCallback();
          }}
          onClick={onClick}
        />
      ))}
    </>
  );
};

export default CheckBoxButtonsComponent;
