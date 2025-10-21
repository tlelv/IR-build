@echo off
setlocal enabledelayedexpansion
cd /d %~dp0
cd ..

if not exist out mkdir out
del /q sources.txt 2>nul
for /r src\main\java %%f in (*.java) do (
  echo %%f>>sources.txt
)

javac -d out @sources.txt
if errorlevel 1 (
  echo Compile failed.
  exit /b 1
)

java -cp out com.cavi.modules.impact.ImpactResponseApp
