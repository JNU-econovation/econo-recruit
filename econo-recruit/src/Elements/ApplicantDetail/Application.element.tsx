import { ApplicationQuestion } from '../../Data/25/Application'

const ApplicantApplicationComponent = ({ isShadow = true }) => {
  const questions = ApplicationQuestion
  const mockAnswer =
    ' 어머님, 보고, 오면 어머니 묻힌 언덕 추억과 계십니다. 말 하나 사랑과 하나에 별 속의 있습니다. 하나에 까닭이요, 벌써 아스라히 별들을 그러나 않은 한 봅니다. 나는 어머니, 벌레는 별빛이 있습니다. 나는 프랑시스 청춘이 가난한 하나에 계십니다. 차 동경과 이름자 버리었습니다. 우는 언덕 멀리 가을로 봅니다. 못 언덕 새워 이웃 차 그리고 나는 계십니다. 무성할 내 때 사랑과 이름을 아직 벌써 있습니다.'

  return (
    <div
      className={
        isShadow
          ? 'drop-shadow-[0_3px_3px_rgba(0,0,0,0.25)] bg-white p-16 w-[98%] font-medium'
          : 'bg-white p-16 w-[98%] font-medium'
      }
    >
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">1. </div>
          <div className="my-4">{questions[0].title}</div>
        </div>
        <div className="flex items-center">
          <div className="text-4xl font-bold">개발자</div>
          <div className="flex flex-col ml-8 gap-1 text-sm font-normal">
            <div>1순위 APP</div>
            <div>2순위 WEB</div>
          </div>
        </div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">2. </div>
          <div className="my-4">{questions[1].title}</div>
        </div>
        <div className="flex items-center mb-12  font-normal">
          <div className="text-4xl font-bold">임채승</div>
          <div className="flex flex-col ml-8 gap-1 text-sm">
            <div>010-1234-5678</div>
            <div>about@econovation.kr</div>
            <div className="flex gap-2">
              <div>183005</div>
              <div>소프트웨어공학과</div>
              <div>1학년 2학기</div>
            </div>
          </div>
        </div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">3. </div>
          <div className="my-4">{questions[2].title}</div>
        </div>
        <div className="flex gap-12 mt-2">
          <div className="flex flex-col gap-1">
            <div>{questions[2].children?.at(0)?.title}</div>
            <div className="font-normal">디자인 스터디 예정</div>
          </div>
          <div className="flex flex-col gap-1">
            <div>{questions[2].children?.at(1)?.title}</div>
            <div className="font-normal">학과 공지사항</div>
          </div>
        </div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">4. </div>
          <div className="my-4">{questions[3].title}</div>
        </div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">5. </div>
          <div className="my-4">{questions[4].title}</div>
        </div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">6. </div>
          <div className="my-4">{questions[5].title}</div>
        </div>
        <div className="text-lg">있다</div>
        <div className="my-4 text-sm">6-1. {questions[5].children?.at(0)?.title}</div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">7. </div>
          <div className="my-4">{questions[6].title}</div>
        </div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">8. </div>
          <div className="my-4">{questions[7].title}</div>
        </div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">9. </div>
          <div className="my-4">{questions[8].title}</div>
        </div>
        <div className="mt-2 font-normal">{mockAnswer}</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">10. </div>
          <div className="my-4">{questions[9].title}</div>
        </div>
        <div className="mt-2 font-normal">확인했습니다.</div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">11. </div>
          <div className="my-4">
            개인정보 수집에 관한 안내 및 개인정보 수집에 대한 안내에 동의하시나요?
          </div>
        </div>
        <div className="flex gap-12 mt-2 text-sm">
          <div className="flex flex-col gap-1">
            <div>개인정보 수집(공통)에 대한 안내</div>
            <div className="flex items-center gap-2 text-sm font-normal my-2">
              <input
                className="accent-black"
                type="checkbox"
                name="agree1"
                id="agree1"
                checked={true}
              />
              <label>동의합니다.</label>
            </div>
          </div>
          <div className="flex flex-col gap-1">
            <div>개인정보 수집(포트폴리오)에 대한 안내</div>
            <div className="flex items-center gap-2 text-sm font-normal my-2">
              <input
                className="accent-black"
                type="checkbox"
                name="agree1"
                id="agree1"
                checked={true}
              />
              <label>동의합니다.</label>
            </div>
          </div>
        </div>
      </div>
      <div className="mb-12">
        <div className="flex items-baseline">
          <div className="w-0 -translate-x-6">12. </div>
          <div className="my-4">{questions[10].title}</div>
        </div>
      </div>
    </div>
  )
}

export default ApplicantApplicationComponent
