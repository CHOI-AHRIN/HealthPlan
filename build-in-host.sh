#!/usr/bin/env bash

# 볼륨 경로가 없으면 생성
VOLUME_PATH="/nfs_shared/spring/upload"
if [ ! -d "$VOLUME_PATH" ]; then
  mkdir -p "$VOLUME_PATH"
  echo "볼륨 경로가 생성되었습니다: $VOLUME_PATH"
fi

# 개발환경
# docker build --no-cache -t localhost:8443/hp-app .
# docker push localhost:8443/hp-app

# docker-compose 실행
# docker-compose up -d

# ---------------------------------------------------


# 운영환경
# Docker 이미지 빌드 및 푸시
docker build --no-cache -t 192.168.1.10:8443/hp-app .
docker push 192.168.1.10:8443/hp-app

# deployment 생성 / 이미지 이름 확인하기
# kubectl apply -f deployment.yaml

# service 생성
# kubectl apply -f service.yaml





