import Txt from "@/components/common/Txt.component";
import { minimumIntegerDigits } from "@/src/functions/replacer";
import { FC } from "react";

interface TimelineRowProps {
  date: Date;
  isLast: boolean;
}

const TimelineRow: FC<TimelineRowProps> = ({ date, isLast }) => {
  const dateString = `${minimumIntegerDigits(
    date.getHours(),
    2
  )}:${minimumIntegerDigits(date.getMinutes(), 2)}`;

  return (
    <span className="flex-1 border-l translate-x-6 mb-8">
      <Txt className="-translate-x-1/2 block w-fit -translate-y-6 h-2">
        {dateString}
      </Txt>
      {isLast && (
        <>
          <input
            type="checkbox"
            name="timeline"
            className="hidden peer"
            id={date.getTime().toString()}
          />
          <label
            htmlFor={date.getTime().toString()}
            className="h-8 block peer-checked:bg-[#2160FF] bg-[#EFEFEF]"
          ></label>
        </>
      )}
    </span>
  );
};

export default TimelineRow;
