import { useSetRecoilState } from 'recoil';
import ApplicantApplicationComponent from '../ApplicantDetail/Application.component';
import ApplicantCommentComponent from '../ApplicantDetail/Comment.component';
import ApplicantLabelComponent from '../ApplicantDetail/Label.component';
import ApplicantUserComponent from '../ApplicantDetail/User.component';
import { applicantPopupBooleanState } from '../../storage/Applicant/Applicant.atom';

const ApplicantPopupComponent = () => {
  const setIsPopuped = useSetRecoilState(applicantPopupBooleanState);
  return (
    <div className="flex justify-center absolute top-0 left-0 z-20 bg-white w-screen h-screen overflow-hidden">
      <div className="w-[90rem] p-20 drop-shadow-[0_8px_8px_rgba(0,0,0,0.25)]">
        <div className="flex flex-col bg-white p-20">
          <button
            onClick={() => {
              setIsPopuped(false);
            }}
          >
            <img className="w-6" src="/ellipsis.multiply.svg" alt="" />
          </button>
          <div className="flex flex-1">
            <div className="w-[40rem] pt-14 h-[calc(100vh-14rem)] overflow-auto">
              <ApplicantUserComponent />
              <ApplicantLabelComponent />
              <ApplicantCommentComponent />
            </div>
            <div className="flex-1 min-w-[40rem]">
              <div className="p-2 overflow-auto max-h-[calc(100vh-14rem)] h-fit">
                <ApplicantApplicationComponent isShadow={false} />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ApplicantPopupComponent;
