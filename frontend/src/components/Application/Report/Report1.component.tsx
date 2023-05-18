import { useReducer } from 'react';
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

  const [onError, setOnError] = useReducer(onErrorReducer, {
    name: false,
    cellphone: false,
    undergrade: false,
    grade: false,
  });

  const beforeCheckCallback =
    name !== '' &&
    cellphone !== '' &&
    undergrade !== '' &&
    grade !== '' &&
    semister !== '' &&
    isCellPhoneNumber(cellphone) &&
    isUndergradeNumber(undergrade);

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
              results: e === '',
            });
          }}
          title={data.value[0].title + ' *'}
          placeholder="홍길동"
          value={name}
        />
        <InputTextWithLabel
          onError={onError.cellphone}
          onChangeText={(e) => {
            const replacedText = e
              .replace(/[^0-9]/g, '')
              .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
            setCellphone(replacedText);
            setOnError({
              type: 'cellphone',
              results: !isCellPhoneNumber(replacedText),
            });
          }}
          title={data.value[1].title + ' *'}
          placeholder="010-1234-5678"
          value={cellphone}
          maxTextLength={13}
        />
        <InputTextWithLabel
          onError={onError.undergrade}
          onChangeText={(e) => {
            const replacedText = e.replace(/[^0-9]/g, '');
            setUndergrade(replacedText);
            setOnError({
              type: 'undergrade',
              results: !isUndergradeNumber(replacedText),
            });
          }}
          title={data.value[2].title + ' *'}
          placeholder="123456"
          value={undergrade}
          maxTextLength={6}
        />
        <div className="pb-4">{data.value[3].title} *</div>
        <div className="grid grid-cols-4 gap-2">
          <RadioButtonsComponent
            groupName="grade"
            radioButtons={Array.from({ length: 4 }).map((_, i) => {
              return { title: `${i + 1}학년`, value: `${i + 1}` };
            })}
            radioSelectedStore={[grade, (v) => setGrade(v)]}
          />
        </div>
        <div className="grid grid-cols-2 gap-2 mt-2">
          {grade ? (
            <RadioButtonsComponent
              groupName="semester"
              radioButtons={Array.from({ length: 2 }).map((_, i) => {
                return { title: `${i + 1}학기`, value: `${i + 1}` };
              })}
              radioSelectedStore={[semister, (v) => setSemister(v)]}
            />
          ) : (
            ''
          )}
        </div>
        <div className="h-8 mt-4">
          {onError.grade ? (
            <div className="text-[#DC0000]">학년 및 학기를 선택해주세요.</div>
          ) : (
            ''
          )}
        </div>
        <ApplicationNextbuttonComponent canNext={beforeCheckCallback} />
      </div>
    </div>
  );
};

export default ApplicationQuestionReport1Component;
