
# Impact Response (Standalone)

A minimal standalone Swing app that lets you:
- Load time series data from CSV (timestamp,value).
- Define threshold rules (>, <, >=, <=, ==), optionally sliding window average.
- Run filters and see matches as a table.
- Intended for integration into CAVI setup tab later (panel is `ImpactResponsePanel`).

## Build
```bash
./gradlew shadowJar
```

## Run
```bash
java -jar build/libs/impact-response-all.jar
```

## CSV Format
Must include a header row: `timestamp,value` and ISO-8601 timestamps.

Example:
```
timestamp,value
2025-01-01T00:00:00Z,10
2025-01-01T01:00:00Z,15
2025-01-01T02:00:00Z,105
```
