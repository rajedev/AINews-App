---
name: very-simple-code-review
description: >
  Performs a quick, focused code review on a file or code snippet.
  Use this skill whenever the user asks to review code, check a file for issues,
  look for bugs, or wants feedback on their code. Trigger even on casual requests
  like "anything wrong with this?", "take a look at this file", "can you check my
  code?", "review this for me", or "what do you think of this code?".
---

## What to do

1. **Identify the target** — look for a file path in the message, code the user pasted inline, or the file they are currently editing. If unclear, ask for the file path before proceeding.
2. **Read the file** — use the Read tool to load it. For pasted code, work directly from what was shared.
3. **Review across five areas** — scan the code and collect findings:
   - **Bugs / correctness**: logic errors, missing null/empty checks, off-by-one, unhandled errors or exceptions
   - **Code quality**: unnecessary complexity, dead code, poor naming, functions doing too much
   - **Security**: hardcoded secrets, injection risks, unsafe operations, missing auth checks
   - **Performance**: unnecessary allocations, blocking calls on the main thread, inefficient loops or data structures
   - **Positive notes**: things done well — good structure, clean error handling, clear naming. Always include at least one.
4. **Output the report** using the format below.

## Output format

```
## Code Review: `path/to/file`

### Issues
- [HIGH] `line N` — <short description of the problem and why it matters>
- [MED]  `line N` — <short description>
- [LOW]  `line N` — <short description>

### Looks good
- <one thing done well>
- <another if applicable>

### Summary
N issues found (X high, Y medium, Z low).
```

**Severity guide:**
- `HIGH` — likely to cause a crash, data loss, security breach, or incorrect behavior
- `MED` — won't break things immediately but will cause problems under load, edge cases, or as the codebase grows
- `LOW` — style, readability, or minor improvement opportunity

If there are no issues in a category, skip it rather than saying "none found." Keep each issue to one line — be direct and specific. Reference the line number when possible.

## Scope

Review what the user asked about. Don't rewrite the code or suggest major refactors unless asked. If the file is very long (500+ lines), focus on the areas most relevant to the user's stated concern, or review the first logical unit (e.g., a class or module) and offer to continue.