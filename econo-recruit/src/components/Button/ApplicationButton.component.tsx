type applicationRadioType = {
  title: string;
  id: string;
};

const ApplicationRadioComponent = ({ title, id }: applicationRadioType) => {
  return (
    <>
      <input type="radio" id={id} value={title} />
      <label htmlFor={id}>{title}</label>
    </>
  );
};

export default ApplicationRadioComponent;
