#!/usr/bin/env bash

CURRENT_FILE_DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
PROJECT_DIR=$(cd "$CURRENT_FILE_DIR" && cd .. && pwd )
cd "${CURRENT_FILE_DIR}"
"${PROJECT_DIR}"/bin/openapi-generator-cli generate -c "${CURRENT_FILE_DIR}/typescript-klay-api-config.yaml"
"${PROJECT_DIR}"/bin/openapi-generator-cli generate -c "${CURRENT_FILE_DIR}/typescript-klay.account-api-config.yaml"
"${PROJECT_DIR}"/bin/openapi-generator-cli generate -c "${CURRENT_FILE_DIR}/typescript-governance-api-config.yaml"
