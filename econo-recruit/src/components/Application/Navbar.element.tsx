import { useRecoilState } from 'recoil'
import { APPLICATION_QUESTION, APPLICATION_REPORT } from '../../data/25/Application'
import { ApplicationNavbarIndexState } from '../../storage/Application/ApplicationNavbar.atom'

const ApplicationNavbarElement = () => {
  const [index, setIndex] = useRecoilState(ApplicationNavbarIndexState)

  const circleClassName = 'h-2 w-2 rounded-full absolute -translate-x-[2.3rem] translate-y-2'

  return (
    <div className="flex pl-8 before:border-l-2 before:-translate-x-8 before:translate-y-4">
      <div className="flex flex-col gap-8">
        <div className="flex items-baseline cursor-pointer" onClick={() => setIndex(0)}>
          <span
            className={
              index === 0 ? ' bg-black ' + circleClassName : 'bg-[#AFAFAF] ' + circleClassName
            }
          ></span>
          <div className={index === 0 ? ' text-black ' : 'text-[#9C9C9C] '}>
            {APPLICATION_REPORT.find((v) => v.id === 1)?.title}
          </div>
        </div>
        <div className="flex items-baseline cursor-pointer" onClick={() => setIndex(1)}>
          <span
            className={
              index === 1 ? ' bg-black ' + circleClassName : 'bg-[#AFAFAF] ' + circleClassName
            }
          ></span>
          <div className={index === 1 ? ' text-black ' : 'text-[#9C9C9C] '}>
            {APPLICATION_REPORT.find((v) => v.id === 2)?.title}
          </div>
        </div>

        <div className="flex items-baseline cursor-pointer" onClick={() => setIndex(2)}>
          <span
            className={
              index === 2 ? ' bg-black ' + circleClassName : 'bg-[#AFAFAF] ' + circleClassName
            }
          ></span>
          <div className={index === 2 ? ' text-black ' : 'text-[#9C9C9C] '}>
            {APPLICATION_REPORT.find((v) => v.id === 3)?.title}
          </div>
        </div>
        {APPLICATION_QUESTION.map((question, qIndex) => (
          <div
            className="flex items-baseline cursor-pointer"
            key={qIndex}
            onClick={() => setIndex(3 + qIndex)}
          >
            <span
              className={
                index === 3 + qIndex
                  ? ' bg-black ' + circleClassName
                  : 'bg-[#AFAFAF] ' + circleClassName
              }
            ></span>
            <div className={index === 3 + qIndex ? ' text-black ' : 'text-[#9C9C9C] '}>
              {question.title}
            </div>
          </div>
        ))}
        <div
          className="flex items-baseline cursor-pointer"
          onClick={() => setIndex(4 + APPLICATION_QUESTION.length)}
        >
          <span
            className={
              index === 4 + APPLICATION_QUESTION.length
                ? ' bg-black ' + circleClassName
                : 'bg-[#AFAFAF] ' + circleClassName
            }
          ></span>
          <div
            className={
              index === 4 + APPLICATION_QUESTION.length ? ' text-black ' : 'text-[#9C9C9C] '
            }
          >
            {APPLICATION_REPORT.find((v) => v.id === 5)?.title}
          </div>
        </div>
      </div>
    </div>
  )
}

export default ApplicationNavbarElement
