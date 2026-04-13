#!/usr/bin/env bash
# PreToolUse — Arch Guard
# Blocks edits where presentation/ files import from data/ layer

INPUT=$(cat)

TOOL=$(echo "$INPUT" | jq -r '.tool_name')
FILE=$(echo "$INPUT" | jq -r '.tool_input.file_path // empty')

# Only care about Edit/Write on .kt files inside presentation/
if [[ "$TOOL" != "Edit" && "$TOOL" != "Write" ]]; then exit 0; fi
if [[ "$FILE" != *"/presentation/"* ]]; then exit 0; fi
if [[ "$FILE" != *.kt ]]; then exit 0; fi

# Get the new content being written
if [[ "$TOOL" == "Edit" ]]; then
  CONTENT=$(echo "$INPUT" | jq -r '.tool_input.new_string // empty')
else
  CONTENT=$(echo "$INPUT" | jq -r '.tool_input.content // empty')
fi

# Check for illegal cross-layer imports
if echo "$CONTENT" | grep -qE "import com\.rajedev\.ainewsapp\.data\."; then
  echo "ARCH VIOLATION: presentation layer must not import from data layer." >&2
  echo "File: $FILE" >&2
  echo "Use domain layer (repository interface / use case) instead." >&2
  exit 2
fi

exit 0