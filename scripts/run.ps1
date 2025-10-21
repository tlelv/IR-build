# PowerShell run script
$ErrorActionPreference = "Stop"
$Root = Split-Path -Parent $MyInvocation.MyCommand.Path
$Root = Join-Path $Root ".."
Set-Location $Root

# Compile
New-Item -ItemType Directory -Force -Path out | Out-Null
$files = Get-ChildItem -Path "src/main/java" -Recurse -Filter *.java | ForEach-Object { $_.FullName }
$files | Out-File -FilePath sources.txt -Encoding ascii
javac -d out @sources.txt

# Run
java -cp out com.cavi.modules.impact.ImpactResponseApp
