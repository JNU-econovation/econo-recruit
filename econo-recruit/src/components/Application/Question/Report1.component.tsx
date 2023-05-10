import { useEffect, useReducer, useState } from 'react';
import { useLocalStorage } from '@/hooks/localstorage.hook';
import { APPLICATION_REPORT } from '@/data/25/Application';
import InputTextWithLabel from '@/components/InputText/InputTextWithLabel.component';
import useApplicationPageControll from '@/hooks/useApplicationPageControll.hook';

type Report1Error = {
  name: boolean;
  cellphone: boolean;
  undergrade: boolean;
  grade: boolean;
};

type OnErrorAction =
  | { type: 'name'; results: boolean }
  | { type: 'cellphone'; results: boolean }
  | { type: 'grade'; results: boolean }
  | { type: 'undergrade'; results: boolean };

const onErrorReducer = (state: Report1Error, action: OnErrorAction) => {
  switch (action.type) {
    case 'name':
      return { ...state, name: action.results };
    case 'cellphone':
      return { ...state, cellphone: action.results };
    case 'undergrade':
      return { ...state, undergrade: action.results };
    case 'grade':
      return { ...state, grade: action.results };
    default:
      return state;
  }
};

const ApplicationQuestionReport1Component = () => {
  const [name, setName] = useLocalStorage('name', '');
  const [cellphone, setCellphone] = useLocalStorage('cellphone', '');
  const [undergrade, setUndergrade] = useLocalStorage('undergrade', '');
  const [grade, setGrade] = useLocalStorage('grade', '');
  const [semister, setSemister] = useLocalStorage('semister', '');

  const { goNextPage, goPrevPage } = useApplicationPageControll();

  const [canNext, setCanNext] = useState(false);

  const [onError, setOnError] = useReducer(onErrorReducer, {
    name: false,
    cellphone: false,
    undergrade: false,
    grade: false,
  });

  useEffect(() => {
    setOnError({ type: 'name', results: name === '' });
    setOnError({ type: 'cellphone', results: cellphone === '' });
    setOnError({ type: 'undergrade', results: undergrade === '' });
    setCanNext(
      onError.cellphone || onError.name || onError.undergrade || onError.grade
    );
  }, [name, cellphone, undergrade, grade, semister]);

  const data = APPLICATION_REPORT[1];
  const nameClassName =
    'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md';
  const nextButtonClassName =
    'flex-1 rounded-md flex justify-center items-center p-4';

  const onNextPage = () => {
    if (!name) {
      setOnError({ type: 'name', results: true });
    }

    if (!cellphone || cellphone.length !== 13) {
      setOnError({ type: 'cellphone', results: true });
    }
    if (!undergrade || undergrade.length !== 6) {
      setOnError({ type: 'undergrade', results: true });
    }
    if (!grade || !semister) {
      setOnError({ type: 'grade', results: true });
    }
    goNextPage();
  };

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
        <InputTextWithLabel
          onError={onError.name}
          onChangeText={(e) => setName(e)}
          title={data.value[0].title + ' *'}
          placeholder="홍길동"
          value={name}
        />
        <InputTextWithLabel
          onError={onError.cellphone}
          onChangeText={(e) =>
            setCellphone(
              e
                .replace(/[^0-9]/g, '')
                .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`)
            )
          }
          title={data.value[1].title + ' *'}
          placeholder="010-1234-5678"
          value={cellphone}
          maxTextLength={13}
        />
        <InputTextWithLabel
          onError={onError.undergrade}
          onChangeText={(e) => setUndergrade(e.replace(/[^0-9]/g, ''))}
          title={data.value[2].title + ' *'}
          placeholder="123456"
          value={undergrade}
          maxTextLength={6}
        />
        <div>
          <div className="pb-4">{data.value[3].title} *</div>
          <div className="flex gap-2">
            {Array.from({ length: 4 }, (_, i) => i + 1).map((i) => (
              <div
                key={`grade${i}`}
                className="w-full"
                onClick={() => {
                  setGrade(`${i}`);
                  setOnError({ type: 'grade', results: false });
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
                      setSemister(`${i}`);
                      setOnError({ type: 'grade', results: false });
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
          {onError.grade ? (
            <div className="text-[#DC0000]">학년 및 학기를 선택해주세요.</div>
          ) : (
            ''
          )}
        </div>
        <div className="flex gap-2 mt-4">
          <button
            className="flex-1 rounded-md flex justify-center items-center p-4 bg-[#EFEFEF]"
            onClick={goPrevPage}
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
  );
};

export default ApplicationQuestionReport1Component;
