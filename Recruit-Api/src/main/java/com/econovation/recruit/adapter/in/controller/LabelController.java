package com.econovation.recruit.adapter.in.controller;


import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruitdomain.domain.label.Label;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LabelController {
    private final LabelUseCase labelUseCase;

    @GetMapping("/labels")
    public ResponseEntity<List<Label>> findByApplicantId(Integer applicantId) {
        List<Label> labels = labelUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(labels, HttpStatus.OK);
    }

    @PostMapping("/labels")
    public ResponseEntity<Label> createLabel(Integer applicantId, Integer idpId) {
        Label label = labelUseCase.createLabel(applicantId, idpId);
        return new ResponseEntity<>(label, HttpStatus.OK);
    }

    @PostMapping("/labels/delete")
    public ResponseEntity<Boolean> deleteLabel(Integer applicantId, Integer idpId) {
        Boolean isDeleted = labelUseCase.deleteLabel(applicantId, idpId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
