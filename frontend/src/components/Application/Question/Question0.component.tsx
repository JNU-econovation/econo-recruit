import { APPLICATION_QUESTION } from '@/data/25/Application';
import { useEffect, useState } from 'react';
import { useAtom } from 'jotai';
import { cloneDeep } from 'lodash-es';
import ApplicationNextbuttonComponent from '@/components/Button/ApplicationNextButton.component';

const ApplicationQuestion0Component = () => {
  const data = APPLICATION_QUESTION[0];
  const [canNext, setCanNext] = useState(false);

  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">3. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title}</div>
        </div>
      </div>
      <div className="w-[30rem]">
        <textarea className="w-full h-[calc(100vh-25rem)] outline-none resize-none border-[#DBDBDB] border-[1px] p-4 rounded-md" />
        <ApplicationNextbuttonComponent canNext={canNext} />
      </div>
    </div>
  );
};

export default ApplicationQuestion0Component;
