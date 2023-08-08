package com.econovation.recruit.api.applicant;


import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApplicantController {
    private static final String APPLY_SUCCESS_MESSAGE = "성공적으로 지원됐습니다";
    private final ApplicantRegisterUseCase applicantRegisterUseCase;

    @Operation(summary = "상품을 장바구니에 담습니다. 상품에 답변해야하는 응답이 있다면, 응답도 보내주시면 됩니다.")
    @ApiErrorExceptionsExample(CreateApplicantExceptionDocs.class)
    @PostMapping("/applicants")
    public ResponseEntity registerApplicant(@RequestBody List<BlockRequestDto> blockElements) {
        applicantRegisterUseCase.execute(blockElements);
        return new ResponseEntity<>(APPLY_SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
