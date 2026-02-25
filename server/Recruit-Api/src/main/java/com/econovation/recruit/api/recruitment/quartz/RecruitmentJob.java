package com.econovation.recruit.api.recruitment.quartz;

import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.exception.RecruitmentNotFoundException;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

// 수행할 작업을 의미하는 클래스
@Component
@RequiredArgsConstructor
public class RecruitmentJob extends QuartzJobBean {

    private final RecruitmentPort repository;
    private final LatestRecruitmentVo recruitmentVo;

    private static final String RECRUITMENT_ID = "recruitmentId";
    private static final String OP = "operation";
    private static final String START_POST_FIX = "_START";
    private static final String END_POST_FIX = "_END";

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        RecruitmentStates targetState;
        Recruitment target =
                repository
                        .findById(data.getLong(RECRUITMENT_ID))
                        .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        if (data.getString(OP).equals("start")) targetState = RecruitmentStates.RECRUITING;
        else if (data.getString(OP).equals("end")) targetState = RecruitmentStates.END;
        else throw new IllegalStateException("job op가 잘못 설정되었습니다.");

        repository.save(target.updateStates(targetState));
        recruitmentVo.refreshRecruitment(target);
    }

    public static JobDetail getStartJob(Long recruitmentId, Integer year) {
        return JobBuilder.newJob(RecruitmentJob.class)
                .withIdentity(startJobKey(recruitmentId))
                .usingJobData(RECRUITMENT_ID, recruitmentId)
                .usingJobData(OP, "start")
                .build();
    }

    public static JobDetail getEndJob(Long recruitmentId, Integer year) {
        return JobBuilder.newJob(RecruitmentJob.class)
                .withIdentity(endJobKey(recruitmentId))
                .usingJobData(RECRUITMENT_ID, recruitmentId)
                .usingJobData(OP, "end")
                .build();
    }

    public static JobKey startJobKey(Long recruitmentId) {
        return new JobKey(recruitmentId + START_POST_FIX);
    }

    public static JobKey endJobKey(Long recruitmentId) {
        return new JobKey(recruitmentId + END_POST_FIX);
    }
}
