## Dauo Project

- 스케줄러 기능이 포함된 API 프로젝트

## 기술 스택
- <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
- <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
- H2
- Bucket4j

## 서버구동 방법
1) jar 파일을 생성한 후 원하는 경로에 배포한다.

## 사용 Guide

1) 프로젝트를 실행한다.
2) .yml 파일에 allow-ips에 허용할 IP를 추가한다.
3) token 값을 얻어온다.
```
    POST /api/v1/authenticate HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    {
        "username" : "dauo", 
        "password" : "dauo"
    } 
```
4) token 값을 헤더에 넣고 api를 실행한다.
```
   GET /api/v1/channel HTTP/1.1
   Host: localhost:8080
   Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlamtpbSIsImV4cCI6MTY2NDEwODQwOCwiaWF0IjoxNjY0MDcyNDA4fQ.ea3xmsP1SjWrHD_Y3AwxkNbHsFFQMAR15Uzj8encUEM
```
