# Claude Code Guide

This file is read automatically by Claude Code at session start.

## Skills

### econo-recruit-maintainer
Read `.ai/skills/econo-recruit-maintainer/SKILL.md` before editing any code in this repository. That file and its references directory define the project rules, module ownership, high-risk areas, and verification steps shared by all agents.

If the task involves the architecture, storage model, or core business flows, also read `.ai/skills/econo-recruit-maintainer/references/architecture.md`.

### git-commit
Read `.ai/skills/git-commit/SKILL.md` before creating any commit in this repository. That file defines the commit message format, allowed type prefixes, splitting heuristics, and Korean writing rules.

For observed examples from recent history, also read `.ai/skills/git-commit/references/commit-patterns.md`.

## Quick Reference

- Real project root: `server/`
- Build and test from `server/` using `.\gradlew.bat` (Windows) or `./gradlew` (Unix)
- Mongo for applicant answer payloads; JPA/MySQL for operational state; Redis for cache/tokens
- Board ordering is a linked list via `nextBoardId` / `nextColumnsId` — not numeric indices
- `.env` is sensitive; never surface its contents in logs, summaries, or code comments
