import { APPLICATION_REPORT, APPLICATION_REPORT_FIELD } from '@/data/25/Application'
import {
  ApplicationNavbarIndexState,
} from '@/storage/Application/Application.atom'
import { useLocalStorage } from '@/hooks/localstorage.hook'
import { useSetAtom } from 'jotai'

const ApplicationQuestionReport0Component = () => {
  const [type, setType] = useLocalStorage('type', '')
  const [field1, setField1] = useLocalStorage('field1', '')
  const [field2, setField2] = useLocalStorage('field2', '')
  const setPage = useSetAtom(ApplicationNavbarIndexState)

  const data = APPLICATION_REPORT[0]
  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">1. </div>
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
            checked={type === 'develop'}
            onChange={() => setType('develop')}
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
            checked={type === 'pm'}
            onChange={() => setType('pm')}
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
            checked={type === 'design'}
            onChange={() => setType('design')}
          />
          <label
            className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked/design:bg-[#303030] peer-checked/design:text-white"
            htmlFor="design"
          >
            디자이너
          </label>
        </div>
        {type ? (
          <div>
            <div className="py-8">1 순위</div>
            <div className="grid grid-cols-3 gap-2 w-[30rem] font-semibold">
              {APPLICATION_REPORT_FIELD.map((field, fieldIndex) => {
                const labelClassName = `border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked:bg-[#303030] peer-checked:text-white peer-disabled:bg-[#F2F2F2] peer-disabled:text-[#B9B9B9]  peer-disabled:cursor-auto`
                const inputClassName = `hidden peer`

                return (
                  <div key={'field-' + fieldIndex + '-input1'}>
                    <input
                      className={inputClassName}
                      type="radio"
                      name="field1"
                      checked={field1 === field}
                      id={`${field}1`}
                      disabled={field2 === field}
                      onChange={() => setField1(field)}
                    />
                    <label className={labelClassName} htmlFor={`${field}1`}>
                      {field}
                    </label>
                  </div>
                )
              })}
            </div>
            {field1 ? (
              <>
                <div className="py-8">2 순위</div>
                <div className="grid grid-cols-3 gap-2 w-[30rem] font-semibold">
                  {APPLICATION_REPORT_FIELD.map((field, fieldIndex) => {
                    const labelClassName = `border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked:bg-[#303030] peer-checked:text-white peer-disabled:bg-[#F2F2F2] peer-disabled:text-[#B9B9B9]  peer-disabled:cursor-auto`
                    const inputClassName = `hidden peer`

                    return (
                      <div key={'field-' + fieldIndex + '-input2'}>
                        <input
                          className={inputClassName}
                          type="radio"
                          name="field2"
                          id={`${field}2`}
                          checked={field2 === field}
                          disabled={field1 === field}
                          onClick={() => setPage(1)}
                          onChange={() => setField2(field)}
                        />
                        <label className={labelClassName} htmlFor={`${field}2`}>
                          {field}
                        </label>
                      </div>
                    )
                  })}
                  <div className="col-span-3">
                    <input
                      className="hidden peer"
                      type="radio"
                      name="field2"
                      id="none2"
                      checked={field2 === 'none'}
                      onClick={() => setPage(1)}
                      onChange={() => setField2('none')}
                    />
                    <label
                      className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer  peer-checked:bg-[#303030] peer-checked:text-white"
                      htmlFor="none2"
                    >
                      선택없음
                    </label>
                  </div>
                </div>
              </>
            ) : (
              ''
            )}
          </div>
        ) : (
          ''
        )}
      </div>
    </div>
  )
}

export default ApplicationQuestionReport0Component
