"use client";

import { ApplicantReq } from "@/src/apis/applicant/applicant";
import { APPLICANT } from "@/src/constants/applicant/26";
import { FC, Fragment } from "react";
import { junctionApplicant } from "./Junction.component";
import Txt from "../common/Txt.component";

interface ApplicantDetailRightProps {
  data: ApplicantReq[];
}

const ApplicantDetailRight: FC<ApplicantDetailRightProps> = ({ data }) => (
  <>
    {APPLICANT.map((node, index) => (
      <Fragment key={index}>
        <div className="flex gap-2">
          <Txt typography="h5">{`${node.id}. `}</Txt>
          <Txt typography="h5">{node.title}</Txt>
        </div>
        {junctionApplicant(node, data)}
      </Fragment>
    ))}
  </>
);

export default ApplicantDetailRight;
