 FROM openjdk:17.0.2-jdk-slim-buster
 LABEL description="Echo IP Java Application"
 EXPOSE 60433

 # git 설치
 RUN apt-get update && apt-get install -y git dos2unix

 # GitHub에서 프로젝트 클론
 RUN git clone https://github.com/CHOI-AHRIN/HealthPlan.git

 # 프로젝트 디렉토리로 이동
 WORKDIR HealthPlan

 # Git에서 클론된 파일들의 줄 끝 형식 자동 변환 설정 (CRLF -> LF)
 RUN git config --global core.autocrlf input

 # mvnw 파일에 실행 권한 부여 및 줄 끝 형식 변환
 RUN dos2unix mvnw && chmod +x mvnw

 # 모든 클론된 파일들의 줄 끝 형식을 LF로 변환 (줄 끝 문제 해결)
 RUN find . -type f -exec dos2unix {} \;

 # 빌드 및 빌드 후 결과 확인
 RUN ./mvnw clean package && ls -l target/

 # mvnw를 사용해 빌드
 # RUN ./mvnw clean package

 # 빌드된 JAR 파일을 /opt로 이동
 RUN mv target/app-in-host.jar /opt/app-in-image.jar

 # 작업 디렉토리 변경
 WORKDIR /opt

 # 컨테이너 시작 시 애플리케이션 실행
 ENTRYPOINT [ "java", "-jar", "app-in-image.jar" ]


