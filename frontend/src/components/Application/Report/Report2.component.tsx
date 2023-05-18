import { useState } from 'react';
import { APPLICATION_REPORT } from '@/data/25/Application';
import ApplicationNextbuttonComponent from '@/components/Button/ApplicationNextButton.component';
import { useLocalStorage } from '@/hooks/localstorage.hook';
import InputTextWithLabel from '@/components/InputText/InputTextWithLabel.component';

const ApplicationQuestionReport2Component = () => {
  const [major, setMajor] = useLocalStorage('major', '');
  const [doubleMajor, setDoubleMajor] = useLocalStorage('doubleMajor', '');
  const [minor, setMinor] = useLocalStorage('minor', '');
  const data = APPLICATION_REPORT[2];
  const [onError, setOnError] = useState(false);
  const majorClassName =
    'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md';

  const canNext = major !== '';

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
          title={data.value[0].title + ' *'}
          onChangeText={(e) => {
            setMajor(e);
            setOnError(e === '');
          }}
          onError={onError}
          value={major}
        />
        <InputTextWithLabel
          title={data.value[1].title ?? ''}
          onChangeText={(e) => setDoubleMajor(e)}
          value={doubleMajor}
        />
        <InputTextWithLabel
          title={data.value[2].title ?? ''}
          onChangeText={(e) => setMinor(e)}
          value={minor}
        />
        <ApplicationNextbuttonComponent canNext={canNext} />
      </div>
    </div>
  );
};

export default ApplicationQuestionReport2Component;
