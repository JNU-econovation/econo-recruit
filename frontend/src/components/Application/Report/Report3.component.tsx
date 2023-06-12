import { APPLICATION_REPORT } from '@/data/25/Application';
import { useState } from 'react';
import ApplicationNextbuttonComponent from '@/components/Button/ApplicationNextButton.component';
import InputTextWithLabel from '@/components/InputText/InputTextWithLabel.component';

const ApplicationQuestionReport3Component = () => {
  const data = APPLICATION_REPORT[3];
  const [onError, setOnError] = useState(false);
  const [canNext, setCanNext] = useState(false);
  const [appData, setAppData] = useState('');
  // const [showEtc, setShowEtc] = useState(appData.supportPathAddtional);
  const workingClassName =
    'p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md';

  // useEffect(() => {
  //   if (appData.supportPath === '[]' && appData.supportPathAddtional === '') {
  //     setCanNext(false);
  //   } else {
  //     setCanNext(true);
  //     setOnError(false);
  //   }
  // }, [appData]);

  return (
    <div className="flex">
      <div className="flex w-[30rem]">
        <div className="text-xl font-semibold ">3. </div>
        <div className="pl-4">
          <div className="text-xl font-semibold pb-4">{data.title}</div>
          <div>{data.subtitle}</div>
        </div>
      </div>
      <div className="w-[30rem]">
        <div>
          <div>
            <InputTextWithLabel
              onChangeText={() => {}}
              title={data.value[0].title ?? ''}
              value={appData}
              subTitle="동아리, 연구실, 아르바이트, 스터디, 교환학생 등"
            />
            {/* <input
              className={workingClassName}
              type="text"
              // value={appData.working}
              // onChange={(e) =>
              //   setAppData((v) => {
              //     const cv = cloneDeep(v);
              //     cv.working = e.target.value;
              //     return cv;
              //   })
              // }
            /> */}
            <div className="h-4"></div>
          </div>
          <div>
            <div className="pb-4">
              {data.value[1].title}* {data.value[1].subtitle}
            </div>
            <div className="grid grid-cols-3 gap-2">
              {/* <CheckBoxButtonsComponent
              checkBoxButtons={data.value[1].value?.map((v: string, i) => { 
                return {title: v,value: v}
              }
             /> */}
              {data.value[1].value?.map((v, i) => (
                <div key={i}>
                  <input
                    type="checkbox"
                    className="hidden peer"
                    name="supportPath"
                    // checked={
                    // (JSON.parse(appData.supportPath) as string[]).find(
                    //   (fv) => fv === v
                    // ) !== undefined
                    // }
                    id={`supportPath${i}`}
                    onChange={() => {
                      // const supportPath = JSON.parse(
                      //   appData.supportPath
                      // ) as string[];
                      // const supportResultData = supportPath.find(
                      //   (fv) => fv === v
                      // )
                      //   ? JSON.stringify(supportPath.filter((fv) => fv !== v))
                      //   : JSON.stringify([...supportPath, v]);
                      // setAppData((av) => {
                      //   const cv = cloneDeep(av);
                      //   cv.supportPath = supportResultData;
                      //   return cv;
                      // });
                      // setOnError(
                      //   supportResultData === '[]' &&
                      //     appData.supportPathAddtional === ''
                      // );
                    }}
                  />
                  <label
                    htmlFor={`supportPath${i}`}
                    className="flex-1 p-4 border-[#DBDBDB] border-[1px] rounded-md flex justify-center items-center peer-checked:bg-[#303030] peer-checked:text-white"
                  >
                    {v}
                  </label>
                </div>
              ))}
              <div>
                <input
                  type="checkbox"
                  className="hidden peer"
                  name="supportPath"
                  // checked={showEtc}
                  id={`supportPath100`}
                  // onChange={() => {
                  //   setShowEtc(!showEtc);
                  //   setAppData((v) => {
                  //     const cv = cloneDeep(v);
                  //     cv.supportPathAddtional = '';
                  //     return cv;
                  //   });
                  //   setOnError(appData.supportPath === '[]');
                  // }}
                />
                <label
                  htmlFor={`supportPath100`}
                  className="flex-1 p-4 border-[#DBDBDB] border-[1px] rounded-md flex justify-center items-center peer-checked:bg-[#303030] peer-checked:text-white"
                >
                  기타
                </label>
              </div>
              <div className="col-span-2">
                {/* {showEtc ? (
                  <input
                    className="p-4 w-full outline-none border-[1px] border-[#DBDBDB] rounded-md"
                    type="text"
                    value={appData.supportPathAddtional}
                    onChange={(e) => {
                      setAppData((v) => {
                        const cv = cloneDeep(v);
                        cv.supportPathAddtional = e.target.value;
                        return cv;
                      });
                      setOnError(
                        appData.supportPath === '[]' &&
                          appData.supportPathAddtional === ''
                      );
                    }}
                  />
                ) : (
                  ''
                )} */}
              </div>
            </div>

            <div className="h-4 mt-4">
              {onError ? (
                <div className="w-fit text-[#DC0000] ">
                  지원 경로를 선택해해주세요.
                </div>
              ) : (
                ''
              )}
            </div>
          </div>
          <ApplicationNextbuttonComponent canNext={canNext} />
        </div>
      </div>
    </div>
  );
};

export default ApplicationQuestionReport3Component;
