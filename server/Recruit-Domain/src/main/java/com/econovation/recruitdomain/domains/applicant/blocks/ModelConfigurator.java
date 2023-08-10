package com.econovation.recruitdomain.domains.applicant.blocks;

@FunctionalInterface
public interface ModelConfigurator<Builder> {

    Builder configure(Builder builder);
}
