# PowerShell run script (Windows)
$ErrorActionPreference = "Stop"
$RepoRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$RepoRoot = Join-Path $RepoRoot ".."
Set-Location $RepoRoot

# Build list of sources
New-Item -ItemType Directory -Force -Path out | Out-Null
Get-ChildItem -Path "src\main\java" -Recurse -Filter *.java | Select-Object -ExpandProperty FullName | Out-File sources.txt -Encoding ascii

# Use cmd to support @sources.txt
cmd /c ""javac" -d out @sources.txt"
if ($LASTEXITCODE -ne 0) { throw "Compile failed." }

cmd /c ""java" -cp out com.cavi.modules.impact.ImpactResponseApp"
