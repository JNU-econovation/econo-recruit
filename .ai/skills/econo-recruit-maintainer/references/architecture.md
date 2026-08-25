# Architecture Reference

## Repository Layout

- Repository root: wrapper files plus `.env`, `README.md`, and the real backend in `server/`.
- `server/settings.gradle`: declares the active Gradle multi-project modules.
- `server/build.gradle`: shared plugin, dependency, JaCoCo, and SonarQube configuration.

## Module Responsibilities

### `Recruit-Api`

- Spring Boot application entrypoint.
- REST controllers, GraphQL schema/resolvers, WebSocket/STOMP config.
- Security config and JWT filter chain.
- Application services and event handlers.
- Recruitment Quartz scheduler and trigger wiring.

### `Recruit-Domain`

- Domain entities and repositories.
- Mongo applicant models and JPA operational models.
- Ports and adaptors.
- Applicant state model and related domain events.
- Redisson and domain-event AOP helpers.

### `Recruit-Infrastructure`

- Redis and Redisson configuration.
- Slack notifier helpers.
- Mail sending, SES, NCP SMS/mail clients.
- External IDP Feign client.
- PDF rendering support.

### `Recruit-Common`

- JWT provider and properties.
- Shared exceptions and error response model.
- Common annotations, aspects, and utilities.

## Storage Model

- MongoDB:
  applicant answers are stored as `MongoAnswer` with a schemaless `qna` map.
- MySQL/JPA:
  recruitment, interviewer, board, columns, card, record, score, labels, comments, timetable, and related operational entities.
- Redis:
  cache and token-support data.
- Axon Server:
  applicant command and query flow.

## Core Business Flows

### Applicant Submission

1. `ApplicantController` accepts the submission payload.
2. `ApplicantRegisterService` validates and emits an Axon command.
3. `AnswerCreatedEventListener` persists the Mongo document and writes a backup JSON file.
4. `ApplicantRegisterEvent` triggers side effects:
   confirmation email, card creation, optional Slack message.

### Recruitment Lifecycle

1. `RecruitmentController` creates or terminates recruitment entries.
2. `RecruitmentService` persists recruitment state.
3. `RecruitmentRegisteredEventHandler` refreshes `LatestRecruitmentVo`, schedules Quartz jobs, and creates common columns and invisible boards.

### Board Workflow

- Boards and columns are ordered through `nextBoardId` and `nextColumnsId`.
- Moving cards or columns rewires linked references rather than updating numeric sort indices.
- Invisible boards are used as structural anchors and should be treated carefully.

### Auth Flow

- `UserController` and `UserService` handle signup, login, logout, password reset, and email verification.
- JWT generation lives in `Recruit-Common`.
- Security filters live in `Recruit-Api/api/config/security`.
- Access token whitelist support exists through the whitelist domain and Redis-backed infrastructure.

## Practical Entry Points

- New API behavior:
  start with the controller, then service/usecase, then domain adaptor/entity.
- Data bug:
  identify whether the source of truth is Mongo or JPA first.
- Notification bug:
  inspect domain event handlers in `Recruit-Api` and providers in `Recruit-Infrastructure`.
- Board movement bug:
  inspect both service logic and linked-list invariants in board and column entities.
- Recruitment date bug:
  inspect `LatestRecruitmentVo`, `RecruitmentService`, `RecruitmentScheduler`, and application properties together.

## Known Issues

- Unresolved merge conflict markers remain in some `Recruit-Api` files.
- The root README has encoding problems and should not be treated as the primary source of truth.
- The repository root `.env` appears to contain real credentials and should be handled as sensitive material.
