#!/bin/bash

set -e
cd "$(dirname "$0")"

echo "backed 컨테이너를 삭제합니다.."
docker rm -f backend

echo "Docker 이미지를 받아옵니다.."
docker pull blackbean99/econo-recruit:latest

echo "Docker 컨테이너를 실행합니다.."
docker-compose up -d --remove-orphans

echo "사용하지 않는 도커 이미지를 삭제합니다.."
docker image prune -a -f

echo "사용하지 않는 도커 볼륨을 삭제합니다.."
docker volume prune -f