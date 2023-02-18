import { APPLICATION_REPORT } from '../../../data/25/Application'

const ApplicationQuestionReport1Component = () => {
  const data = APPLICATION_REPORT[1]
  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">2. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title} *</div>
          <div>{data.subtitle}</div>
        </div>
      </div>
    </div>
  )
}

export default ApplicationQuestionReport1Component
