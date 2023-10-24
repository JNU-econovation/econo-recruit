package com.econovation.recruit.supports;

import com.econovation.recruit.RecruitApplication;
import com.econovation.recruitcommon.RecruitCommonApplication;
import com.econovation.recruitdomain.RecruitDomainApplication;
import com.econovation.recruitinfrastructure.RecruitInfrastructureApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** 스프링 부트 설정의 컴포넌트 스캔범위를 지정 통합 테스트를 위함 */
@Configuration
@ComponentScan(
        basePackageClasses = {
            RecruitInfrastructureApplication.class,
            RecruitDomainApplication.class,
            RecruitCommonApplication.class,
            RecruitApplication.class,
        })
public class ApiIntegrateTestConfig {}
