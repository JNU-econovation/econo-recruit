package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruit.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LabelController {
    private final LabelUseCase labelUseCase;
    @PostMapping("/labels")
    public ResponseEntity<Label> createLabel(Integer applicantId, Integer idpId){
        Label label = labelUseCase.createLabel(applicantId, idpId);
        return new ResponseEntity<>(label, HttpStatus.OK);
    }
}