package com.econovation.recruit.adapter.in.controller;


import com.econovation.recruit.application.port.in.ResumeUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruitdomain.domains.dto.ResumeInsertDto;
import com.econovation.recruitdomain.domains.resume.Resume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ResumeController {
    private final ResumeUseCase resumeUseCase;
    private final EntityMapper entityMapper;
    //    @PostMapping("/resumes")
    //    public ResponseEntity< List<ResumeInsertDto> > resumeUseCase(@RequestBody HashMap<String,
    // Object> param){
    //        List<ResumeInsertDto> resumeInsertDtos = resumeUseCase.submitResume(param);
    //        return new ResponseEntity<>(resumeInsertDtos, HttpStatus.OK);
    //    }

    @PostMapping("/resumes")
    public ResponseEntity<List<Resume>> submitResume(
            @RequestBody List<ResumeInsertDto> resumesDto) {
        List<Resume> resumes = entityMapper.toResumes(resumesDto);
        return new ResponseEntity(resumeUseCase.submitResume(resumes), HttpStatus.OK);
    }

    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> findAll() {
        List<Resume> all = resumeUseCase.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/resumes/applicant")
    public ResponseEntity<List<Resume>> findByApplicantId(Integer applicantId) {
        List<Resume> all = resumeUseCase.findByApplicantId(applicantId);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/resumes/{id}")
    public ResponseEntity<Resume> findById(@RequestParam(value = "id") Integer resumeId) {
        Resume resume = resumeUseCase.findById(resumeId);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }
}
