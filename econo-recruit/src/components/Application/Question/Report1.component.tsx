import { APPLICATION_REPORT } from '../../../data/25/Application'
import {
  ApplicationNavbarIndexState,
} from '../../../storage/Application/Application.atom'
import { cloneDeep } from 'lodash'
import { useEffect, useState } from 'react'
import { useLocalStorage } from '../../../hooks/localstorage.hook'
import { useSetAtom } from 'jotai'

const ApplicationQuestionReport1Component = () => {
  const [name, setName] = useLocalStorage('name', '')
  const [cellphone, setCellphone] = useLocalStorage('cellphone', '')
  const [undergrad, setUndergrad] = useLocalStorage('undergrad', '')
  const [grade, setGrade] = useLocalStorage('grade', '')
  const [semister, setSemister] = useLocalStorage('semister', '')

  const setPage = useSetAtom(ApplicationNavbarIndexState)
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
      // v.name = false
      v.cellphone = false
      v.undergrad = false
      return cv
    })
    return
  })

  useEffect(() => {
    setOnError((v) => {
      const cv = cloneDeep(v)
      v.name = name === ''
      v.cellphone = cellphone === ''
      v.undergrad = undergrad === ''
      return cv
    })
    if (
      name === '' ||
      cellphone === '' ||
      cellphone.length !== 13 ||
      undergrad === '' ||
      undergrad.length !== 6 ||
      grade === '' ||
      semister === ''
    ) {
      setCanNext(false)
    } else {
      setCanNext(true)
    }
  }, [name, cellphone, undergrad, grade, semister])

  const data = APPLICATION_REPORT[1]
  const nameClassName = 'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md'
  const nextButtonClassName = 'flex-1 rounded-md flex justify-center items-center p-4'

  const onNextPage = () => {
    if (!name) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.name = true
        return cv
      })
      return
    }

    if (!cellphone || cellphone.length !== 13) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.cellphone = true
        return cv
      })
      return
    }
    if (!undergrad || undergrad.length !== 6) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        v.undergrad = true
        return cv
      })
      return
    }
    if (!grade || !semister) {
      setOnError((v) => {
        const cv = cloneDeep(v)
        cv.grade = true
        return cv
      })
      return
    }
    setPage(2)
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
            value={name}
            onChange={(e) => setName(e.target.value)}
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
            value={cellphone}
            maxLength={13}
            onChange={(e) => {
              setCellphone(
                e.target.value
                  .replace(/[^0-9]/g, '')
                  .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`)
              )
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
            value={undergrad}
            onChange={(e) => {
              setUndergrad(e.target.value.replace(/[^0-9.]/g, ''))
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
                  setGrade(`${i}`)
                  setOnError((v) => {
                    const cv = cloneDeep(v)
                    cv.grade = false
                    return cv
                  })
                }}
              >
                <input
                  type="radio"
                  name="grade"
                  id={`grade${i}`}
                  className={`hidden peer`}
                  checked={+grade === i}
                />
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
            {grade ? (
              <>
                {Array.from({ length: 2 }, (_, i) => i + 1).map((i) => (
                  <div
                    key={`semister${i}`}
                    className="w-full"
                    onClick={() => {
                      setSemister(`${i}`)
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
                      className="hidden peer"
                      checked={+semister === i}
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
          <button
            className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
            onClick={() => setPage(0)}
          >
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
