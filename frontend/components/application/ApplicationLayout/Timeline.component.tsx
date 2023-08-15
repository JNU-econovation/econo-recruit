"use client";

import { ApplicationTimeline } from "@/src/constants/application/type";
import { useParams } from "next/navigation";
import { FC } from "react";

interface TimelineRowProps {
  start_time: Date;
  end_time: Date;
}

const TimelineRow: FC<TimelineRowProps> = ({ start_time, end_time }) => {
  return <div></div>;
};

const ApplicationTimelineLayout = () => {
  const params = useParams();
  const data = require(`@/src/constants/application/${params.generation}.ts`);
  const { disable_time, time, seperate } =
    data.APPLICATION_TIMELINE as ApplicationTimeline;

  return (
    <div>
      {time.map((time, index) => (
        <div key={index}>{time.start_time.toUTCString()}</div>
      ))}
    </div>
  );
};

export default ApplicationTimelineLayout;
