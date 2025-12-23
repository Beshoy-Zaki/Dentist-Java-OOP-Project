#!/usr/bin/env bash
set -euo pipefail

# Simple test runner for this workspace
# Usage: ./run-tests.sh [module]
# Default module: Doctor

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MODULE="${1:-Doctor}"

echo "Running Maven tests in '$MODULE' (project root: $ROOT_DIR)"
cd "$ROOT_DIR/$MODULE"

# If SDKMAN exists, try to load it so tests use configured JDK
if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  # shellcheck disable=SC1091
  set +u
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  set -u
elif [ -f "/usr/local/sdkman/bin/sdkman-init.sh" ]; then
  # shellcheck disable=SC1091
  set +u
  source "/usr/local/sdkman/bin/sdkman-init.sh"
  set -u
fi

# Run Maven test; forward any additional args
mvn test "${@:2}"
