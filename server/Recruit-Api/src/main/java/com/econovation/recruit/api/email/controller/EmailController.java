package com.econovation.recruit.api.email.controller;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email.service.ApplicantEmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "access-token")
@RestController
@RequestMapping("/api/v1")
@Tag(name = "[8.0] email 관련 API", description = "메일 API")
@RequiredArgsConstructor
public class EmailController {

    private final ApplicantEmailService emailService;
    private final ApplicantQueryUseCase applicantQueryUseCase;

    @PostMapping("/emails/{applicantId}")
    public ResponseEntity<String> send(@PathVariable String applicantId) {
        return ResponseEntity.ok("");
    }
}
