package com.econovation.recruit.utils;

import com.econovation.recruit.api.applicant.usecase.ApplicantCommandUseCase;
import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import io.vavr.concurrent.Future;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "data.init.disabled", havingValue = "false", matchIfMissing = true)
public class AnswerMongoDBMigration implements ApplicationRunner {
    private final ApplicantCommandUseCase applicantCommandUseCase;
    private final ApplicantQueryUseCase applicantQueryUseCase;

    @Value("${econovation.year}")
    private Integer year;

    @PostConstruct
    public void init() throws IOException, SQLException {
        // init.sql 파일을 읽어와서 실행합니다.
        log.info("MongoDB Migration을 시작합니다. Mysql -> MongoDB");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Map<String, Object>> answers = applicantQueryUseCase.execute();
        Future.of(() -> answers)
                .map(
                        answer -> {
                            return answer.stream()
                                    .map(
                                            map -> {
                                                Map<String, Object> qna =
                                                        map.entrySet().stream()
                                                                .collect(
                                                                        Collectors.toMap(
                                                                                Entry::getKey,
                                                                                Entry::getValue));
                                                applicantCommandUseCase.execute(qna);
                                                return qna;
                                            })
                                    .collect(Collectors.toList());
                        })
                .onSuccess(
                        (qna) -> {
                            log.info("MongoDB Migration을 완료했습니다. Mysql -> MongoDB");
                        })
                .onFailure(
                        (exception) -> {
                            log.error("MongoDB Migration을 실패했습니다. Mysql -> MongoDB");
                        });
    }
}
