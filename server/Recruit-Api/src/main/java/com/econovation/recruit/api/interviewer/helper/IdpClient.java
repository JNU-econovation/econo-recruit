package com.econovation.recruit.api.interviewer.helper;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "IdpClient", url = "https://auth.econovation.kr")
@Headers("Content-Type: application/json; charset=UTF-8")
public class IdpClient {}
