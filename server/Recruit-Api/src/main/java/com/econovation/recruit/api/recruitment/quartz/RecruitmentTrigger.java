package com.econovation.recruit.api.recruitment.quartz;

import java.time.ZonedDateTime;
import java.util.Date;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class RecruitmentTrigger {

    public static Trigger get(JobKey jobKey, ZonedDateTime startAt){
        return TriggerBuilder.newTrigger()
                .forJob(jobKey)
                .startAt(Date.from(startAt.toInstant()))
                .build();
    }

}
