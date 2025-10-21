@echo off
setlocal enabledelayedexpansion
cd /d %~dp0

REM Go to project root if script is nested
if exist src\main\java (
  cd .
) else (
  cd ..
)

if not exist out mkdir out
del /q sources.txt 2>nul

for /r src\main\java %%f in (*.java) do (
  echo %%f>>sources.txt
)

echo Compiling sources...
javac -d out @sources.txt
if errorlevel 1 (
  echo Compile failed.
  exit /b 1
)

echo Launching Impact Response...
java -cp out com.cavi.modules.impact.ImpactResponseApp
