type applicationRadioType = {
  title: string
  id: string
}

const ApplicationRadioElement = ({ title, id }: applicationRadioType) => {
  return (
    <>
      <input type="radio" id={id} value={title} />
      <label htmlFor={id}>{title}</label>
    </>
  )
}

export default ApplicationRadioElement
