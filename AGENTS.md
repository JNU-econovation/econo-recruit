# Agent Guide

This repository contains a Spring Boot recruitment backend. The real application lives under `server/`; the repository root is mostly a wrapper.

If you are an agent that does not support native skill loading, read `.ai/skills/econo-recruit-maintainer/SKILL.md` before editing code. That file is written in plain Markdown on purpose so Codex, Claude, and similar agents can all use it.
If the task is specifically about creating commits or writing commit messages, also read `.ai/skills/git-commit/SKILL.md`.

## Start Here

1. Treat `server/` as the project root for build, test, and code search.
2. Read `server/settings.gradle`, `server/build.gradle`, and `server/Recruit-Api/src/main/resources/application.yml` first.
3. Map the task to the correct module before editing code.

## Module Map

- `server/Recruit-Api`: HTTP, GraphQL, WebSocket, security, schedulers, application services, event handlers.
- `server/Recruit-Domain`: domain entities, adaptors, ports, Mongo/JPA models, domain events, state logic.
- `server/Recruit-Infrastructure`: Redis, Slack, email, NCP, AWS SES, IDP Feign clients, PDF rendering.
- `server/Recruit-Common`: JWT, shared annotations, exceptions, utility code, AOP helpers.

## Runtime Shape

- Applicant submissions are stored in MongoDB as schemaless `qna` documents.
- Operational entities such as recruitment, boards, records, interviewers, and scores are stored with JPA/MySQL.
- Redis is used for cache and token-related support data.
- Axon handles command/query flow around applicant registration.
- Quartz schedules recruitment start and end transitions.

## High-Risk Areas

- Applicant registration is event-driven:
  `ApplicantController` -> `ApplicantRegisterService` -> Axon command -> Mongo save -> domain event -> email/card/Slack side effects.
- Board ordering is modeled as linked lists through `nextBoardId` and `nextColumnsId`, not by simple position columns.
- Invisible board rows are special and are assumed by the board relocation logic.
- `LatestRecruitmentVo` is a shared snapshot used by several flows; refresh logic matters.
- The repository currently contains unresolved merge conflict markers in some files under `server/Recruit-Api`. Resolve them deliberately if you touch those areas.

## Guardrails

- Do not print, copy, or rotate secrets from `.env`. Treat the file as sensitive.
- Keep edits module-local when possible; do not move domain logic into controllers.
- Preserve the mixed storage model: Mongo for applicant answer payloads, JPA for operational state.
- Preserve event-driven side effects unless the task explicitly changes behavior.
- Prefer targeted tests and targeted searches. Use `rg` for search.

## Common Commands

Run these from `server/`.

- Windows: `.\gradlew.bat test`
- Unix-like: `./gradlew test`
- Narrow test: `.\gradlew.bat :Recruit-Api:test`
- Local stack: `docker-compose up -d`

## Maintenance Workflow

1. Identify the user-facing flow and the owning module.
2. Inspect adjacent handlers, services, adaptors, and configuration before editing.
3. Make the smallest coherent change that matches the current architecture.
4. Verify with the narrowest Gradle task you can reasonably run.
5. Summarize any remaining risks, especially around events, scheduling, security, or linked-list board ordering.
