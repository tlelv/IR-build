
# Impact Response (Standalone; CAVI-aligned)

This repo is a clean, runnable Swing app implementing an Impact Response feature.
It is packaged under `com.cavi.modules.impact` to make future integration into CAVI straightforward.

## Run (no Gradle required)

### macOS/Linux
```bash
./scripts/run.sh
```

### Windows (PowerShell)
```powershell
scripts\run.ps1
```

The scripts compile sources to `out/` and launch the app.

## Build with Gradle (optional)

If you have Gradle installed:
```bash
gradle run
gradle shadowJar
```

If you want a Gradle wrapper in this repo:
```bash
gradle wrapper
# then use ./gradlew run
```

## CSV Format

`timestamp,value` header with ISO-8601 timestamps:
```
timestamp,value
2025-01-01T00:00:00Z,10
2025-01-01T01:00:00Z,15
2025-01-01T02:00:00Z,105
```
