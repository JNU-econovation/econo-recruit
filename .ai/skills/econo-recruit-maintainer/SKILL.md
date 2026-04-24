---
name: econo-recruit-maintainer
description: Maintain, debug, and extend the econo-recruit backend. Use this skill when working in this repository on bug fixes, refactors, feature work, incident triage, build or test failures, auth changes, recruitment lifecycle logic, applicant submission flow, board workflow, or external integrations. Read this skill before editing code under `server/`.
---

# Econo Recruit Maintainer

## Overview

Use this skill to work safely inside the `econo-recruit` repository. The application is a multi-module Spring Boot backend with mixed persistence, event-driven applicant registration, and linked-list style board ordering.

If your agent does not support native skill loading, read this file directly and then read `references/architecture.md`.

## Workflow

1. Treat `server/` as the real project root.
2. Inspect `server/settings.gradle`, `server/build.gradle`, and `server/Recruit-Api/src/main/resources/application.yml`.
3. Map the task to the owning module before making edits.
4. Read the nearest controller, service, adaptor, entity, and config classes involved in the flow.
5. Preserve existing architectural boundaries unless the task explicitly asks for structural change.
6. Verify with the narrowest useful Gradle test task from `server/`.

## Module Ownership

- `Recruit-Api`: controllers, GraphQL resolvers, security, schedulers, orchestration, event handlers.
- `Recruit-Domain`: domain entities, ports, adaptors, state transitions, Mongo and JPA persistence models.
- `Recruit-Infrastructure`: Redis, Slack, mail, NCP, AWS SES, PDF, external IDP clients.
- `Recruit-Common`: JWT, exception model, annotations, AOP, shared utilities.

## Project-Specific Rules

- Keep applicant answer payload logic compatible with schemaless Mongo `qna` documents.
- Keep operational state logic in JPA-backed entities and adaptors.
- Preserve the applicant event chain unless behavior is intentionally changing:
  controller -> service -> Axon command/event -> Mongo save -> domain event -> email/card/Slack side effects.
- Treat board order as linked structure via `nextBoardId` and `nextColumnsId`.
- Do not casually remove or repurpose invisible boards; some logic assumes them.
- Treat `.env` as sensitive and avoid surfacing secrets in logs, docs, or summaries.

## High-Risk Files And Flows

- Applicant registration and query flow in `Recruit-Api/api/applicant` and `Recruit-Domain/domains/applicant`.
- Recruitment scheduling and latest-state snapshot in `Recruit-Api/api/recruitment`.
- Board, column, card movement logic in `Recruit-Api/api/card` and `Recruit-Domain/domains/board`.
- Auth, login, whitelist, and JWT handling across `Recruit-Api/api/config/security`, `Recruit-Api/api/user`, and `Recruit-Common`.
- External notification and messaging integrations in `Recruit-Infrastructure`.

## Known Sharp Edges

- The repository currently has unresolved merge conflict markers in at least:
  `server/Recruit-Api/src/main/java/com/econovation/recruit/api/applicant/service/AnswerCommandService.java`
  `server/Recruit-Api/src/main/java/com/econovation/recruit/api/applicant/dto/GetApplicantsStatusResponse.java`
- The root `README.md` contains encoding issues, so trust the code and config files over the README for precise behavior.
- Some strings and comments are encoded inconsistently; avoid changing text encoding unless the task requires it.

## Verification

Run commands from `server/`.

- Full tests: `.\gradlew.bat test` or `./gradlew test`
- API tests only: `.\gradlew.bat :Recruit-Api:test`
- Build only: `.\gradlew.bat build`
- Local services: `docker-compose up -d`

Prefer targeted verification. If a full test run is too heavy, run the smallest task that exercises the changed module and say what you did not verify.

## References

- Read `references/architecture.md` for the repository map, runtime model, and common maintenance entry points.
