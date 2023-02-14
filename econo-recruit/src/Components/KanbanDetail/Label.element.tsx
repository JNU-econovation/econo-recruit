import { useState } from 'react'

const KanbanLabelElement = () => {
  const [label, setLabel] = useState(['윤성', '민주', '서현', '수민', '규민', '채승'])
  const allLabel = [
    '윤성',
    '건형',
    '시현',
    '민주',
    '서현',
    '수민',
    '시형',
    '서하',
    '규민',
    '채승',
    '영규',
    '수미',
  ]
  const [openAddtional, setOpenAddtional] = useState(false)
  const additionalButton =
    'text-[#2160FF] bg-[#E8EFFF] translate-y-[2px] h-8 w-8 rounded-full flex items-center justify-center transition-all'

  const toggleOpen = () => {
    setOpenAddtional(!openAddtional)
  }

  return (
    <div className="my-12">
      <div>
        <div className="text-lg font-semibold">
          라벨링<span className="text-base font-normal ml-2">{label.length}개</span>
        </div>
        <div className="flex items-baseline gap-2">
          <div className="grid grid-cols-6 gap-2 my-4 w-fit">
            {openAddtional
              ? allLabel
                  .sort((a, b) => label.findIndex((v) => v === b))
                  .map((l) => {
                    if (label.findIndex((v) => v === l) === -1) {
                      return (
                        <button className={'py-1 px-4 rounded-full text-[#777777] bg-[#EFEFEF] '}>
                          {l}
                        </button>
                      )
                    }
                    return (
                      <div className={'py-1 px-4 rounded-full text-[#2160FF] bg-[#E8EFFF]'}>
                        {l}
                      </div>
                    )
                  })
              : label.map((l) => (
                  <div className="text-[#2160FF] bg-[#E8EFFF] py-1 px-4 rounded-full">{l}</div>
                ))}
          </div>
          <button
            onClick={toggleOpen}
            className={openAddtional ? 'rotate-45 ' + additionalButton : additionalButton}
          >
            <img src="/ellipsis.plus.blue.svg" alt="" />
          </button>
        </div>
      </div>
    </div>
  )
}

export default KanbanLabelElement
