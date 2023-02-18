import { useRecoilState, useSetRecoilState } from 'recoil'
import { APPLICATION_REPORT } from '../../../data/25/Application'
import {
  ApplicationNavbarIndexState,
  ApplicationResultDataState,
} from '../../../storage/Application/Application.atom'
import { cloneDeep } from 'lodash'
import { useEffect, useState } from 'react'

const ApplicationQuestionReport1Component = () => {
  const [appData, setAppData] = useRecoilState(ApplicationResultDataState)
  const setPage = useSetRecoilState(ApplicationNavbarIndexState)
  const [canNext, setCanNext] = useState(false)

  const [onError, setOnError] = useState({
    name: false,
    cellphone: false,
    undergrad: false,
    grade: false,
  })
  useEffect(() => {
    setOnError((v) => {
      const cv = cloneDeep(v)
      v.name = appData.name === ''
      v.cellphone = appData.cellphone === ''
      v.undergrad = appData.undergrad === ''
      return cv
    })
    if (
      !appData.name ||
      !appData.cellphone ||
      appData.cellphone.length !== 13 ||
      !appData.undergrad ||
      appData.undergrad.length !== 6 ||
      !appData.grade ||
      !appData.semester
    ) {
      setCanNext(false)
    } else {
      setCanNext(true)
    }
  }, [appData])

  const data = APPLICATION_REPORT[1]
  const nameClassName = 'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md'
  const nextButtonClassName = 'flex-1 rounded-md flex justify-center items-center p-4'

  const onNextPage = () => {
    if (!appData.name) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.name = true
        return cv
      })
    }

    if (!appData.cellphone || appData.cellphone.length !== 13) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.cellphone = true
        return cv
      })
    }
    if (!appData.undergrad || appData.undergrad.length !== 6) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.undergrad = true
        return cv
      })
    }
    if (!appData.grade || !appData.semester) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        cv.grade = true
        return cv
      })
    }
  }

  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">2. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title} *</div>
          <div>{data.subtitle}</div>
        </div>
      </div>
      <div className="w-[30rem]">
        <div>
          <div className="pb-4">{data.value[0].title} *</div>
          <input
            className={onError.name ? '!border-[#DC0000] ' + nameClassName : nameClassName}
            type="text"
            placeholder="홍길동"
            value={appData.name}
            onChange={(e) =>
              setAppData((v) => {
                const cv = cloneDeep(v)
                cv.name = e.target.value
                return cv
              })
            }
          />
          <div className="h-4">
            {onError.name ? (
              <div className="w-full translate-x-[100%]">
                <div className="w-fit text-[#DC0000] -translate-x-[calc(100%+1rem)] -translate-y-10">
                  {data.value[0].title}을 입력해주세요.
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
        </div>
        <div>
          <div className="pb-4">{data.value[1].title} *</div>
          <input
            className={onError.cellphone ? '!border-[#DC0000] ' + nameClassName : nameClassName}
            type="text"
            placeholder="010-1234-5678"
            value={appData.cellphone}
            maxLength={13}
            onChange={(e) => {
              setAppData((v) => {
                const cv = cloneDeep(v)
                cv.cellphone = e.target.value
                  .replace(/[^0-9]/g, '')
                  .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`)
                return cv
              })
            }}
          />
          <div className="h-4">
            {onError.cellphone ? (
              <div className="w-full translate-x-[100%]">
                <div className="w-fit text-[#DC0000] -translate-x-[calc(100%+1rem)] -translate-y-10">
                  {data.value[1].title}을 입력해주세요.
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
        </div>
        <div>
          <div className="pb-4">{data.value[2].title} *(ex. 123456)</div>
          <input
            className={onError.undergrad ? '!border-[#DC0000] ' + nameClassName : nameClassName}
            placeholder="123456"
            type="text"
            maxLength={6}
            value={appData.undergrad}
            onChange={(e) => {
              setAppData((v) => {
                const cv = cloneDeep(v)
                cv.undergrad = e.target.value.replace(/[^0-9.]/g, '')
                return cv
              })
            }}
          />
          <div className="h-4">
            {onError.undergrad ? (
              <div className="w-full translate-x-[100%]">
                <div className="w-fit text-[#DC0000] -translate-x-[calc(100%+1rem)] -translate-y-10">
                  {data.value[2].title}을 입력해주세요.
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
        </div>
        <div>
          <div className="pb-4">{data.value[3].title} *</div>
          <div className="flex gap-2">
            {Array.from({ length: 4 }, (_, i) => i + 1).map((i) => (
              <div
                key={`grade${i}`}
                className="w-full"
                onClick={() => {
                  setAppData((v) => {
                    const cv = cloneDeep(v)
                    cv.grade = `${1}`
                    return cv
                  })
                  setOnError((v) => {
                    const cv = cloneDeep(v)
                    cv.grade = false
                    return cv
                  })
                }}
              >
                <input type="radio" name="grade" id={`grade${i}`} className={`hidden peer`} />
                <label
                  htmlFor={`grade${i}`}
                  className="flex-1 p-4 border-[#DBDBDB] border-[1px] rounded-md flex justify-center items-center peer-checked:bg-[#303030] peer-checked:text-white"
                >
                  {i}학년
                </label>
              </div>
            ))}
          </div>
          <div className="flex gap-2 mt-2">
            {appData.grade ? (
              <>
                {Array.from({ length: 2 }, (_, i) => i + 1).map((i) => (
                  <div
                    key={`semister${i}`}
                    className="w-full"
                    onClick={() => {
                      setAppData((v) => {
                        const cv = cloneDeep(v)
                        cv.semester = `${1}`
                        return cv
                      })
                      setOnError((v) => {
                        const cv = cloneDeep(v)
                        cv.grade = false
                        return cv
                      })
                    }}
                  >
                    <input
                      type="radio"
                      name="semester"
                      id={`semester${i}`}
                      className={`hidden peer`}
                    />
                    <label
                      htmlFor={`semester${i}`}
                      className="flex-1 p-4 border-[#DBDBDB] border-[1px] rounded-md flex justify-center items-center peer-checked:bg-[#303030] peer-checked:text-white"
                    >
                      {i}학년
                    </label>
                  </div>
                ))}
              </>
            ) : (
              ''
            )}
          </div>
        </div>
        <div className="h-8 mt-4">
          {onError.grade ? <div className="text-[#DC0000]">학년 및 학기를 선택해주세요.</div> : ''}
        </div>
        <div className="flex gap-2 mt-4">
          <button className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]">
            이전
          </button>
          <button
            onClick={onNextPage}
            className={
              canNext
                ? nextButtonClassName + ' bg-[#303030] text-white'
                : nextButtonClassName + ' bg-[#EFEFEF] text-[#C8C8C8]'
            }
          >
            다음
          </button>
        </div>
      </div>
    </div>
  )
}

export default ApplicationQuestionReport1Component
