import { useEffect, useReducer, useState } from 'react';
import { useLocalStorage } from '@/hooks/localstorage.hook';
import { APPLICATION_REPORT } from '@/data/25/Application';
import InputTextWithLabel from '@/components/InputText/InputTextWithLabel.component';
import RadioButtonsComponent from '@/components/Button/Radio/RadioButtons.component';
import ApplicationNextbuttonComponent from '@/components/Button/ApplicationNextButton.component';
import { isCellPhoneNumber, isUndergradeNumber } from '@/utils/validator';

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

  const [canNext, setCanNext] = useState(false);

  const [onError, setOnError] = useReducer(onErrorReducer, {
    name: false,
    cellphone: false,
    undergrade: false,
    grade: false,
  });

  const beforeCheckCallback = () => {
    setOnError({ type: 'name', results: name === '' });
    setOnError({
      type: 'cellphone',
      results: cellphone === '' || isCellPhoneNumber(cellphone),
    });
    setOnError({
      type: 'grade',
      results: grade === '' || semister === '',
    });
    setOnError({
      type: 'undergrade',
      results: undergrade === '' || isUndergradeNumber(undergrade),
    });

    return (
      onError.cellphone || onError.name || onError.undergrade || onError.grade
    );
  };

  const data = APPLICATION_REPORT[1];

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
          onChangeText={(e) => {
            setName(e);
            setOnError({
              type: 'name',
              results: false,
            });
            setCanNext(true);
          }}
          title={data.value[0].title + ' *'}
          placeholder="홍길동"
          value={name}
        />
        <InputTextWithLabel
          onError={onError.cellphone}
          onChangeText={(e) => {
            setCellphone(
              e
                .replace(/[^0-9]/g, '')
                .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`)
            );
            setOnError({
              type: 'cellphone',
              results: false,
            });
            setCanNext(true);
          }}
          title={data.value[1].title + ' *'}
          placeholder="010-1234-5678"
          value={cellphone}
          maxTextLength={13}
        />
        <InputTextWithLabel
          onError={onError.undergrade}
          onChangeText={(e) => {
            setUndergrade(e.replace(/[^0-9]/g, ''));
            setOnError({
              type: 'undergrade',
              results: false,
            });
            setCanNext(true);
          }}
          title={data.value[2].title + ' *'}
          placeholder="123456"
          value={undergrade}
          maxTextLength={6}
        />
        <div>
          <div className="pb-4">{data.value[3].title} *</div>
          <div className="flex gap-2">
            <RadioButtonsComponent
              groupName="grade"
              radioButtons={Array(4).map((_, i) => {
                return { title: `${i + 1}학년`, value: `${i + 1}` };
              })}
              radioSelectedStore={[grade, (v) => setGrade(v)]}
            />
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
        <ApplicationNextbuttonComponent
          canNext={canNext}
          beforeCheckCallback={beforeCheckCallback}
        />
      </div>
    </div>
  );
};

export default ApplicationQuestionReport1Component;
