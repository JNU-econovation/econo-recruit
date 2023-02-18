import { APPLICATION_REPORT, APPLICATION_REPORT_FIELD } from '../../../data/25/Application'
import { useRecoilState, useSetRecoilState } from 'recoil'
import {
  ApplicationNavbarIndexState,
  ApplicationResultDataState,
} from '../../../storage/Application/Application.atom'
import { cloneDeep } from 'lodash'

const ApplicationQuestionReport0Component = () => {
  const [applicationData, setApplicationData] = useRecoilState(ApplicationResultDataState)
  const setPage = useSetRecoilState(ApplicationNavbarIndexState)

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
            checked={applicationData.type === 'develop'}
            onChange={() =>
              setApplicationData((v) => {
                const cv = cloneDeep(v)
                cv.type = 'develop'
                return cv
              })
            }
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
            checked={applicationData.type === 'pm'}
            onChange={() =>
              setApplicationData((v) => {
                const cv = cloneDeep(v)
                cv.type = 'pm'
                return cv
              })
            }
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
            checked={applicationData.type === 'design'}
            onChange={() =>
              setApplicationData((v) => {
                const cv = cloneDeep(v)
                cv.type = 'design'
                return cv
              })
            }
          />
          <label
            className="border-[1px] rounded-lg py-4 flex justify-center items-center cursor-pointer peer-checked/design:bg-[#303030] peer-checked/design:text-white"
            htmlFor="design"
          >
            디자이너
          </label>
        </div>
        {applicationData.type ? (
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
                      checked={applicationData.field1 === field}
                      id={`${field}1`}
                      disabled={applicationData.field2 === field}
                      onChange={() =>
                        setApplicationData((v) => {
                          const cv = cloneDeep(v)
                          cv.field1 = field
                          return cv
                        })
                      }
                    />
                    <label className={labelClassName} htmlFor={`${field}1`}>
                      {field}
                    </label>
                  </div>
                )
              })}
            </div>
            {applicationData.field1 ? (
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
                          checked={applicationData.field2 === field}
                          disabled={applicationData.field1 === field}
                          onClick={() => setPage(1)}
                          onChange={() =>
                            setApplicationData((v) => {
                              const cv = cloneDeep(v)
                              cv.field2 = field
                              return cv
                            })
                          }
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
                      checked={applicationData.field2 === 'none'}
                      onClick={() => setPage(1)}
                      onChange={() =>
                        setApplicationData((v) => {
                          const cv = cloneDeep(v)
                          cv.field2 = 'none'
                          return cv
                        })
                      }
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
