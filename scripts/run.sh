#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"
cd "$ROOT_DIR"

# Compile
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt

# Run
java -cp out com.cavi.modules.impact.ImpactResponseApp
