import { Suspense, lazy } from 'react';
import ApplicationNavbarComponent from '@/components/Application/Navbar.component';
import { useParams } from 'react-router-dom';
import { ApplicationNavbarIndexState } from '@/storage/Application/Application.atom';
import { useAtomValue } from 'jotai';

const ApplicationQuestionReport0Component = lazy(
  () => import('@/components/Application/Question/Report0.component')
);

const ApplicationQuestionReport1Component = lazy(
  () => import('@/components/Application/Question/Report1.component')
);

const ApplicationQuestionReport2Component = lazy(
  () => import('@/components/Application/Question/Report2.component')
);

const ApplicationQuestionReport3Component = lazy(
  () => import('@/components/Application/Question/Report3.component')
);

const ApplicationQuestion0Component = lazy(
  () => import('@/components/Application/Question/Question0.component')
);

const ApplicationPage = () => {
  const { period } = useParams();
  const pageIndex = useAtomValue(ApplicationNavbarIndexState);
  const page = [
    <ApplicationQuestionReport0Component />,
    <ApplicationQuestionReport1Component />,
    <ApplicationQuestionReport2Component />,
    <ApplicationQuestionReport3Component />,
    <ApplicationQuestion0Component />,
  ];

  return (
    <div className="pt-24 flex">
      <div className="pl-12 w-[36rem]">
        <ApplicationNavbarComponent />
      </div>
      <div className="pl-32 w-full">
        <div className="pb-6 text-3xl font-bold border-b-4 w-full">
          {period}기 신입모집
        </div>
        <div className="pt-6">
          <Suspense>{page[pageIndex]}</Suspense>
        </div>
      </div>
    </div>
  );
};

export default ApplicationPage;
