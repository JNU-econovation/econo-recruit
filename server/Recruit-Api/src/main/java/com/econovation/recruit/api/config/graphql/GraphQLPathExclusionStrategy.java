package com.econovation.recruit.api.config.graphql;

import com.econovation.recruit.api.config.PathExclusionStrategy;
import org.springframework.stereotype.Component;

@Component
public class GraphQLPathExclusionStrategy implements PathExclusionStrategy {

    @Override
    public boolean shouldExclude(String requestPath) {
        return requestPath.startsWith("/api/graphql");
    }
}
