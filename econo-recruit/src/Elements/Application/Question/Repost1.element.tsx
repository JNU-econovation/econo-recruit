import { useEffect, useState } from 'react'
import { APPLICATION_REPORT } from '../../../Data/25/Application'

const ApplicationQuestionReport1 = () => {
  const [selectedType, setSelectedType] = useState('')

  const data = APPLICATION_REPORT[0]
  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div>1. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title} *</div>
          <div>{data.subtitle}</div>
        </div>
      </div>
      <div>
        <div className="flex-1 grid grid-cols-2 gap-2 w-[30rem] font-semibold">
          <input
            className="hidden peer/develop"
            type="radio"
            name="area"
            id="develop"
            onChange={() => setSelectedType('develop')}
          />
          <label
            className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked/develop:bg-[#303030] peer-checked/develop:text-white"
            htmlFor="develop"
          >
            개발자
          </label>
          <input
            className="hidden peer/pm"
            type="radio"
            name="area"
            id="pm"
            onChange={() => setSelectedType('pm')}
          />
          <label
            className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked/pm:bg-[#303030] peer-checked/pm:text-white"
            htmlFor="pm"
          >
            기획자
          </label>
          <input
            className="hidden peer/design"
            type="radio"
            name="area"
            id="design"
            onChange={() => setSelectedType('design')}
          />
          <label
            className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked/design:bg-[#303030] peer-checked/design:text-white"
            htmlFor="design"
          >
            디자이너
          </label>
        </div>
        {selectedType !== '' ? (
          <div>
            <div className="py-8">1 순위</div>
            <div>asdf</div>
          </div>
        ) : (
          ''
        )}
      </div>
    </div>
  )
}

export default ApplicationQuestionReport1
