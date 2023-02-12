import { useState } from 'react'

type KanbanAddColumnComponent = {
  AddColumnCallBack: () => {}
}

const KanbanAddColumnComponent = ({ AddColumnCallBack }: KanbanAddColumnComponent) => {
  const [isOpenAddCard, setIsOpenAddCard] = useState(false)
  const inputClassName =
    'p-3 border-[1px] border-[#F0F0F0] drop-shadow-md bg-white rounded-lg w-full text-sm my-3 outline-none '

  return (
    <div className="mt-2">
      <input
        type="text"
        className={isOpenAddCard ? inputClassName + 'block' : inputClassName + 'hidden'}
        placeholder="Enter a title for this card"
      />
      {isOpenAddCard ? (
        <div className="flex gap-3 justify-end">
          <button onClick={() => setIsOpenAddCard(false)}>
            <img src="/public/ellipsis.multiply.svg" alt="" />
          </button>
          <button onClick={AddColumnCallBack}>
            <img src="/public/arrow.forward.circle.fill.svg" alt="" />
          </button>
        </div>
      ) : (
        <button
          className="flex gap-2 justify-center items-center text-[#828282]"
          onClick={() => {
            setIsOpenAddCard(true)
          }}
        >
          <img src="/public/ellipsis.plus.svg" alt="AddColumn" />
          Add a card
        </button>
      )}
    </div>
  )
}

export default KanbanAddColumnComponent
