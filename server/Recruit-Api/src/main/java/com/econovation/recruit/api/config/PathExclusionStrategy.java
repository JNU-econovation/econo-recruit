package com.econovation.recruit.api.config;

public interface PathExclusionStrategy {
    boolean shouldExclude(String path);
}
