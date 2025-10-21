#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
# If launched from scripts/, go up; else stay
if [ -d "src/main/java" ]; then
  : # already at root
else
  cd ..
fi

mkdir -p out
find src/main/java -name "*.java" > sources.txt
echo "Compiling sources..."
javac -d out @sources.txt
echo "Launching Impact Response..."
java -cp out com.cavi.modules.impact.ImpactResponseApp
