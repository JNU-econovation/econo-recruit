---
name: git-commit
description: Write git commits in this repository's established Korean commit style. Use this skill when creating one or more commits, splitting a diff into smaller commits, choosing a commit type, or drafting commit messages that match recent repository history. Prefer this skill for commit work in `econo-recruit`.
---

# Git Commit

## Overview

Use this skill to turn working tree changes into small, reviewable commits that match this repository's observed commit history.

The default message format in this repository is:

`[type]: 한글 메시지`

Read `references/commit-patterns.md` if you need evidence from recent history or examples.

## Commit Workflow

1. Inspect the diff and separate unrelated changes before committing.
2. Prefer the smallest commit that leaves the codebase in a coherent state.
3. If a change contains two intentions, split it into two commits.
4. Write the title in Korean.
5. Use the repository's dominant title format unless the task explicitly requires ticket-style commit titles.

## Default Title Format

Use:

`[type]: 한글 메시지`

Examples:

- `[feat]: 지원자 이름 자동완성 API 추가`
- `[fix]: 토큰 조회 전 null 체크`
- `[refactor]: 컨트롤러 책임을 서비스로 분리`
- `[docs]: 스웨거 설명 보강`
- `[style]: spotless`
- `[chore]: springdoc 의존성 추가`

## Allowed Types

- `feat`: new user-facing or operator-facing behavior
- `fix`: bug fix, validation fix, null handling, permission fix, runtime correction
- `refactor`: structural cleanup without intended behavior change
- `docs`: documentation or static asset documentation updates
- `style`: formatting-only changes, especially spotless
- `chore`: dependency, config, or maintenance work that does not fit better elsewhere
- `test`: test-only additions or fixes
- `ci`: workflow or pipeline-only changes

If unsure between `feat` and `fix`, ask:

- Did behavior exist but work incorrectly? Use `fix`.
- Is behavior newly added? Use `feat`.

## Ticket-Style Exception

This repository also contains titles like:

`[BE-166] 상태 변경 불가 기간에 상태 변경 시 예외 발생 (#426)`

Treat this as an exception used for issue- or PR-linked commits, often merge or squash-oriented history. Do not use it by default for ordinary local commits unless the user explicitly wants the ticket number included.

## Message Writing Rules

- Keep the title to a single line.
- Do not add a period at the end.
- Prefer concrete behavior over vague summaries.
- Describe what changed, not how hard it was.
- Avoid mixing multiple concerns in one title.
- Keep English only for identifiers, library names, env vars, or domain terms already used in code.

Good:

- `[fix]: 지원서 생성 시 보드 연결 순서 오류 수정`
- `[feat]: 댓글 전체 공개 상태 조회 추가`
- `[refactor]: 지원서 QNA Key 상수로 치환`

Bad:

- `[fix]: 여러 버그 수정`
- `[feat]: 이것저것 추가`
- `update`

## Splitting Heuristics

Split commits when any of these are true:

- one part is behavior change and another part is cleanup
- one part is schema or config change and another part is feature logic
- one file change could be reverted independently from the rest
- formatting noise can be separated from logic
- tests can be committed independently after the production change

Do not over-split into commits that fail tests or leave references broken.

## Practical Guidance For Agents

- Prefer non-interactive staging if the environment is agentic or terminal-limited.
- Stage only the files for the current intent.
- Re-check `git diff --cached` before writing the title.
- If the staged diff is too broad, unstage and split first.

## References

- Read `references/commit-patterns.md` for observed examples from more than 20 recent non-merge commits.
