package com.econovation.recruitinfrastructure.idp.client;

import com.econovation.recruitdomain.domains.dto.InterviewerResponse;
import feign.Headers;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IdpClient", url = "https://auth.econovation.kr")
@Headers("Content-Type: application/json; charset=UTF-8")
public interface IdpClient {
    @GetMapping("/api/users")
    List<InterviewerResponse> loadByName(String name);

    @GetMapping("/api/users/{user-id}")
    InterviewerResponse loadById(@PathVariable(name = "user-id") Integer idpId);
}
