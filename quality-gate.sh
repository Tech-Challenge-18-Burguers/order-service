#!/bin/bash
echo "Waiting for SonarQube analysis to complete..."
sleep 20
result=$(curl -u "$SONAR_TOKEN": https://sonarcloud.io/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY)
status=$(echo "$result" | jq -r '.projectStatus.status')

echo "SonarQube Quality Gate status: $status"
if [ "$status" != "OK" ]; then
    echo "SonarQube Quality Gate failed"
    exit 1
else
    echo "SonarQube Quality Gate passed"
fi