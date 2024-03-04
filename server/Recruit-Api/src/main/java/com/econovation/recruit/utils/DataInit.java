package com.econovation.recruit.utils;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "data.init.disabled", havingValue = "false", matchIfMissing = true)
public class DataInit {
    private final DataSource dataSource;
    private final InterviewerRecordPort interviewerRecordPort;
    private final PasswordEncoder passwordEncoder;

    @Value("${data.init.admin.id}")
    private String adminEmail;

    @Value("${data.init.admin.password}")
    private String adminPassword;

    @PostConstruct
    public void init() throws IOException, SQLException {
        // init.sql 파일을 읽어와서 실행합니다.
        log.info("초기화 sql을 실행합니다.");
        ClassPathResource resource = new ClassPathResource("init.sql");
        String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        log.info(sql);
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        String encededPassword = passwordEncoder.encode(adminPassword);
        Interviewer admin =
                Interviewer.builder()
                        .name("이서현")
                        .role(Role.ROLE_OPERATION)
                        .year(21)
                        .email(adminEmail)
                        .password(encededPassword)
                        .build();
        interviewerRecordPort.save(admin);
    }
}
