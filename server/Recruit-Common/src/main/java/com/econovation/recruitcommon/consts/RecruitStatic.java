package com.econovation.recruitcommon.consts;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecruitStatic {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String WITHDRAW_PREFIX = "DELETED:";
    public static final String TOKEN_ROLE = "role";
    public static final String TOKEN_TYPE = "type";
    public static final String TOKEN_ISSUER = "Recruit";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String KR_YES = "예";
    public static final String KR_NO = "아니요";
    public static final Integer DEVELOPER_COLUMNS_ID = 0;
    public static final Integer DESIGNER_COLUMNS_ID = 1;
    public static final Integer PLANNER_COLUMNS_ID = 2;

    public static final int MILLI_TO_SECOND = 1000;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER = 500;
    public static final String APPLICANT_SUCCESS_REGISTER_MESSAGE = "성공적으로 지원됐습니다";
    public static final String NO_MATCH_INTERVIEWER_MESSAGE = "해당하는 면접관이 없습니다.";
    public static final String BOARD_SUCCESS_REGISTER_MESSAGE = "성공적으로 업무카드가 등록됐습니다";
    public static final String BOARD_SUCCESS_DELETE_MESSAGE = "성공적으로 업무카드가 삭제되었습니다.";
    public static final String LABEL_SUCCESS_DELETE_MESSAGE = "성공적으로 라벨이 삭제되었습니다.";

    public static final String NO_MATCH_COMMENT_MESSAGE = "조회된 Comment가 존재하지 않습니다";
    public static final String NO_MATCH_COMMENTLIKE_MESSAGE = "조회된 Comment가 존재하지 않습니다";
    public static final String COMMENT_SUCCESS_REGISTER_MESSAGE = "성공적으로 댓글이 등록됐습니다";
    public static final List<Map.Entry<String, String>> SUBMIT_APPLICANT_EVENT_SEPERATOR_LIST =
            new ArrayList<Map.Entry<String, String>>(
                    List.of(
                            new AbstractMap.SimpleEntry<>("프로젝트 희망 분야를 선택해주세요.", "hopeField"),
                            new AbstractMap.SimpleEntry<>("이름", "name")));
    public static final List<Map.Entry<String, String>> APPLICANT_SEPERATOR_LIST =
            new ArrayList<Map.Entry<String, String>>(
                    List.of(
                            new AbstractMap.SimpleEntry<>("프로젝트 희망 분야를 선택해주세요.", "hopeField"),
                            new AbstractMap.SimpleEntry<>("1순위", "firstPriority"),
                            new AbstractMap.SimpleEntry<>("2순위", "secondPriority"),
                            new AbstractMap.SimpleEntry<>("이름", "name"),
                            new AbstractMap.SimpleEntry<>("연락처", "phoneNumber"),
                            new AbstractMap.SimpleEntry<>("학번", "studentId"),
                            new AbstractMap.SimpleEntry<>("학년", "grade"),
                            new AbstractMap.SimpleEntry<>("학기", "semester"),
                            new AbstractMap.SimpleEntry<>("전공", "major"),
                            new AbstractMap.SimpleEntry<>("복수전공", "doubleMajor"),
                            new AbstractMap.SimpleEntry<>("부전공", "minor"),
                            new AbstractMap.SimpleEntry<>("지원 경로 * (중복 선택 가능)", "supportPath"),
                            new AbstractMap.SimpleEntry<>("이메일", "email")));
    public static final List<String> TIMETABLE_APPLICANT_FIELD = List.of("name", "hopeField");
    public static final Long NO_START_NUMBER = 1000000L;
    public static final Long MINIMUM_PAYMENT_WON = 1000L;
    public static final Integer APPLICANT_REGISTER_NAVIGATION_LOCATION = 0;
    public static final Long ZERO = 0L;

    public static final String KAKAO_OAUTH_QUERY_STRING =
            "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

    public static final String[] SwaggerPatterns = {
        "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
    };
}
