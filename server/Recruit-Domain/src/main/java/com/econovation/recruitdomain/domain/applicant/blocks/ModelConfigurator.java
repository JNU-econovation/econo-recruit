package com.econovation.recruitdomain.domain.applicant.blocks;

@FunctionalInterface
public interface ModelConfigurator<Builder> {

    Builder configure(Builder builder);
}
