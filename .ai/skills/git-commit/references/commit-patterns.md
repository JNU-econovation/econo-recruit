# Commit Patterns Reference

This reference summarizes observed commit title patterns from 40 recent non-merge commits in this repository.

## Observed Default Pattern

The dominant current pattern is:

`[type]: 한글 메시지`

Common types in recent history:

- `feat`
- `fix`
- `refactor`
- `docs`
- `style`
- `chore`

There is also an older mixed style without square brackets:

- `feat: ...`
- `fix: ...`
- `refactor: ...`
- `style: ...`

Prefer the newer bracketed form because it is the clearer recent convention.

## Ticket-Linked Pattern

Recent history also contains ticket-linked titles:

- `[BE-166] 상태 변경 불가 기간에 상태 변경 시 예외 발생 (#426)`
- `[BE-165] 합격자 공지 시 슬랙 알림 발송하기 (#425)`
- `[BE-157] 권한 변경 API 관리자만 허용  (#407)`

This appears to be used for issue- or PR-linked work rather than for every local commit.

## Sample Titles From History

Below are observed commit titles from recent non-merge history.

1. `[fix]: 운영 상위 워크플로우에서 SPRING_PROFILES_ACTIVE를 prod로 수정`
2. `[fix]: Jib 이미지 실행 프로필을 환경변수 기반으로 수정`
3. `[BE-166] 상태 변경 불가 기간에 상태 변경 시 예외 발생 (#426)`
4. `[BE-165] 합격자 공지 시 슬랙 알림 발송하기 (#425)`
5. `[docs]: 포트폴리오 PDF 업데이트 (#422)`
6. `[fix]: 토큰 조회 전 null 체크`
7. `[fix]: 지원서 생성 시 보드 연결 순서 오류 수정 (#417)`
8. `[style]: spotless`
9. `[fix]: GlobalExceptionHandler에서 인증 없는 요청의 예외 처리 시 SecurityContextNotFoundException 발생 방지`
10. `[fix]: 중복키예외를 트랜잭션 밖으로 보내지 않음`
11. `[feat]: mongodb 중복 키 예외 axonserver에서 재시도하지 않음`
12. `[fix]: 숫자에 잘못된 어노테이션`
13. `[refactor]: 회원가입 이메일 인증 로직 주석 해제`
14. `[style]: spotless`
15. `[fix]: 필드 널 유효성 검사`
16. `[BE-146] 합/불 상태 변경 가능 여부 계산 로직 추가 (#386)`
17. `[BE-157] 권한 변경 API 관리자만 허용  (#407)`
18. `[BE-156] 운영 환경에서 Swagger 접속을 막기 (#405)`
19. `refactor: 지원서 QNA Key (ID, YEAR, MAJOR, CREATED_AT) 상수화`
20. `refactor: 지원서 QNA Key 하드코딩 -> ApplicantQnaKeys 상수로 치환`
21. `refactor: 컨트롤러의 책임 서비스로 분리`
22. `feat: 지원서 Qna Key 상수 정의`
23. `[feat]: 스웨거 요청 형식 지정`
24. `[chore]: @Schema 어노테이션을 사용하기 위한 springdoc 의존성 추가`
25. `[refactor]: 불필요한 코드 제거`
26. `[feat]: 댓글 전체 공개 상태 조회 시큐리티 설정 추가`
27. `[feat]: 댓글 전체 공개 상태 조회`
28. `[BE-152] SQL 문법 오류 제거 + 회원가입시 이메일 인증 코드 주석 처리 (#399)`
29. `feat: 초기화 sql 추가`
30. `refactor: 40기까지의 컬럼 데이터 초기화 (#396)`
31. `fix: 잘못된 예외 타입 수정`
32. `style: spotless`
33. `feat: 댓글 공개 여부에 따라 블러 처리`
34. `refactor: 용어 통일`
35. `feat: 코멘트 전체 공개 전환 기능 구현`
36. `refactor: 시간 형식을 long에서 LocalDateTime으로 변경`
37. `refactor: 블러 코멘트 문구 수정`
38. `feat: 다른 면접관의 댓글은 볼 수 있지만 블러 처리`
39. `feat: 코멘트 조회 시 TF들은 서로의 코멘트 조회 불가`
40. `feat: 게스트는 해당 요청 불가`

## Practical Conclusion

- Default to `[type]: 한글 메시지`.
- Keep commits small and single-purpose.
- Use ticket-style `[BE-123] ...` only when the user explicitly wants issue-linked titles.
- Use `style` for formatting-only commits such as spotless.
