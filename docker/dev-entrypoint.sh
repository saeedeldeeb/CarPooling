#!/bin/sh

# Watch for source changes and trigger recompilation in background
(
  while true; do
    inotifywait -r -q -e modify,create,delete,move /app/src
    sleep 1
    echo "[watcher] Source changed, recompiling..."
    ./mvnw compile -q 2>&1
  done
) &

# Run Spring Boot (includes initial compile + DevTools watches target/classes)
exec ./mvnw spring-boot:run
