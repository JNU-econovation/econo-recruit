import { APPLICATION_QUESTION } from '../../../data/25/Application'
import {
  ApplicationNavbarIndexState,
  ApplicationResultDataState,
} from '../../../storage/Application/Application.atom'
import { useEffect, useState } from 'react'
import { cloneDeep } from 'lodash'
import { useAtom, useSetAtom } from 'jotai'

const ApplicationQuestion0Component = () => {
  const [appData, setAppData] = useAtom(ApplicationResultDataState)
  const setPage = useSetAtom(ApplicationNavbarIndexState)
  const data = APPLICATION_QUESTION[0]
  const nextButtonClassName = 'flex-1 rounded-md flex justify-center items-center p-4'
  const [canNext, setCanNext] = useState(false)

  useEffect(() => {
    if (appData.question[0]) {
      setCanNext(false)
    } else {
      setCanNext(true)
    }
  }, [appData])

  const onNextPage = () => {
    setPage(5)
  }

  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">3. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title}</div>
        </div>
      </div>
      <div className="w-[30rem]">
        <textarea
          className="w-full h-[calc(100vh-25rem)] outline-none resize-none border-[#DBDBDB] border-[1px] p-4 rounded-md"
          onChange={(e) => {
            setAppData((v) => {
              const cv = cloneDeep(v)
              cv.question[0] = e.target.value
              return cv
            })
          }}
        />
        <div className="flex gap-2 mt-4">
          <button
            className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
            onClick={() => setPage(3)}
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

export default ApplicationQuestion0Component
