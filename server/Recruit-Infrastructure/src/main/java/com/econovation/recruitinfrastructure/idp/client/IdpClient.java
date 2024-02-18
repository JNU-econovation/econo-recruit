package com.econovation.recruitinfrastructure.idp.client;

import com.econovation.recruitinfrastructure.idp.config.IdpConfig;
import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "IdpClient",
        url = "https://auth.econovation.kr",
        configuration = IdpConfig.class)
public interface IdpClient {
    @GetMapping("/api/users")
    List<InterviewerResponse> loadByName(String name);
    // TODO @RequestHeader("Authorization") String accessToken

    @GetMapping("/api/users/{user-id}")
    InterviewerResponse loadById(@PathVariable(name = "user-id") Long userId);
}
