"use client";

import { TimelineCell } from "@/components/application/applicationLayout/timeline/Timeline.component";
import Txt from "@/components/common/Txt.component";
import { CURRENT_GENERATION } from "@/src/constants";
import { ApplicationTimeline } from "@/src/constants/application/type";
import { dateSplicer } from "@/src/functions/date";
import { minimumIntegerDigits } from "@/src/functions/replacer";
import classNames from "classnames";
import { FC, useEffect, useState } from "react";

interface ApplicantTimelineNodeProps {
  generation: string;
  postId: string;
}

const ApplicantTimelineNode: FC<ApplicantTimelineNodeProps> = ({
  generation,
  postId,
}) => {
  const data = require(`@/src/constants/application/${CURRENT_GENERATION}.ts`);
  const { disableTime, time, seperate } =
    data.APPLICATION_TIMELINE as ApplicationTimeline;
  const [timeline, setTimeline] = useState<number[]>([]);

  useEffect(() => {
    // setTimeline(())
  }, []);

  return (
    <div>
      {time.map((time, index) => (
        <div className="w-full">
          <Txt
            typography="h6"
            className="block pb-8"
          >{`${time.startTime.getMonth()}월 ${time.startTime.getDate()}일`}</Txt>
          <div className="w-full flex">
            {dateSplicer(time.startTime, time.endTime, seperate).map(
              (date, index) => (
                <span className="flex-1 border-l translate-x-6 mb-8">
                  <Txt className="-translate-x-1/2 block w-fit -translate-y-6 h-2">
                    {`${minimumIntegerDigits(
                      date.getHours(),
                      2
                    )}:${minimumIntegerDigits(date.getMinutes(), 2)}`}
                  </Txt>
                  {dateSplicer(time.startTime, time.endTime, seperate)
                    .length !==
                    index + 1 && (
                    <div
                      className={classNames("h-8 block bg-[#EFEFEF]", {
                        "bg-[#2160FF]": timeline.includes(index),
                      })}
                    />
                  )}
                </span>
              )
            )}
          </div>
        </div>
      ))}
    </div>
  );
};

export default ApplicantTimelineNode;
