#!/usr/bin/env bash
# PostToolUse — Kotlin Lint
# Runs ktlintCheck after any .kt file is written/edited

INPUT=$(cat)

TOOL=$(echo "$INPUT" | jq -r '.tool_name')
FILE=$(echo "$INPUT" | jq -r '.tool_input.file_path // empty')

if [[ "$TOOL" != "Edit" && "$TOOL" != "Write" ]]; then exit 0; fi
if [[ "$FILE" != *.kt ]]; then exit 0; fi

PROJECT_ROOT="/Users/lruser/Documents/development/AINewsApp"

echo "Running ktlint on: $FILE"
cd "$PROJECT_ROOT" || exit 0

OUTPUT=$(./gradlew ktlintCheck 2>&1)
EXIT_CODE=$?

if [[ $EXIT_CODE -ne 0 ]]; then
  echo "ktlint found issues:"
  echo "$OUTPUT" | grep -A2 "\.kt:" | head -40
else
  echo "ktlint passed."
fi

exit 0