package com.econovation.recruit.supports;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * activeProfile 의 Resolver 를 지정 통합테스트에 필요한 properties 인 common, infrastructure , domain 을 지정하기 위함.
 */
public class ApiIntegrateProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        // some code to find out your active profiles
        return new String[] {"common", "infrastructure", "domain"};
    }
}
