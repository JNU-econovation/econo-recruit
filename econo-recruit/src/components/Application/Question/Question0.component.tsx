import { APPLICATION_QUESTION } from '@/data/25/Application';
import { ApplicationResultDataState } from '@/storage/Application/Application.atom';
import { useEffect, useState } from 'react';
import { useAtom } from 'jotai';
import { cloneDeep } from 'lodash-es';
import ApplicationNextbuttonComponent from '@/components/Button/ApplicationNextButton.component';

const ApplicationQuestion0Component = () => {
  const [appData, setAppData] = useAtom(ApplicationResultDataState);
  const data = APPLICATION_QUESTION[0];
  const [canNext, setCanNext] = useState(false);

  useEffect(() => {
    if (appData.question[0]) {
      setCanNext(false);
    } else {
      setCanNext(true);
    }
  }, [appData]);

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
              const cv = cloneDeep(v);
              cv.question[0] = e.target.value;
              return cv;
            });
          }}
        />
        <ApplicationNextbuttonComponent canNext={canNext} />
      </div>
    </div>
  );
};

export default ApplicationQuestion0Component;
