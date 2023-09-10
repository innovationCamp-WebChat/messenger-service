
![image](https://github.com/innovationCamp/messenger-service/assets/132903726/65141935-2590-4aaf-98b3-561a88d44bf4)

# WebChat
### 대규모 트래픽 처리가 가능한 실시간 채팅 서비스
- 180개 국가에서 대규모의 사람이 이용하는 WhatsApp채팅 앱 서비스를 오마주한 채팅 서비스
- 현대 사회에 필수불가결한 메신저 어플의 대용량 데이터 발생과 이로 인한 부하를 견뎌내기 위해 필요한 대책을 직접 리서치 및 적용

[WebChat 발표자료](https://github.com/innovationCamp/messenger-service/files/12566998/WebChat.pdf)
---
## 프로젝트 목표
### 1. 초당 5000건의 동시 트래픽을 감당하는 채팅 서비스
- 채팅 메세지 전송/수신 max 1000ms
- 채팅 메세지 영구 저장
- 실시간 서버 모니터링
- 스케일 아웃 가능한 서버

### 2. 스파이크 테스트로 안정성 있는 트래픽 관리
- 단계별로 아래의 테스트 조건들을 상향시키면서 에러율과 최대지연시간의 결과와 원인을 분석하며 성능 개선
    - 동시간대 접속 유저 : 100~5000명
    - 초당 보내는 채팅 수 : 100~5000개
    - 분당 보내는 총 채팅 수 : 6000~300,000개
    - 응답 시간 제한 : 1000ms, 2000ms

### 3. 실시간으로 누적되는 데이터 처리
- 분당 최대 300000건의 데이터들을 수용하기 위한 DB 성능 개선
- 경제적이고 효율적인 DB선정을 위한 데이터베이스 시스템 분석
    - MySQL
    - MongoDB
    - Cassandra

### 4. 전자지갑 동시성 제어
- 검색 성능 개선

---
## 영상
- [최종 발표 영상](https://youtu.be/5LnQwj8_g30)
- [간단 홍보 영상](https://youtu.be/uscni2WGS4U)
- 핵심 기능 시연 영상

| 채팅 | 전자지갑 |
|---|---|
|<img src="https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/359d9fff-f095-40bc-8477-423a6797acc3" alt="채팅" width="450" height="300">|<img src="https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/60081942-aa29-40bd-aa87-30ec767617f3" alt="전자지갑" width="450" height="300">|

| Jmeter 테스트 | 모니터링 |
|---|---|
|<img src="https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/57300f9d-79fe-48b2-a028-1cdc5a5375bf" alt="jmeter-레포트" width="450" height="300">|<img src="https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/97956b46-bebc-49ca-8d11-73e8fae72674" alt="부하 테스트 시작" width="450" height="300">|

---
## 서비스 아키텍처 ⚙️
![image](https://github.com/innovationCamp/messenger-service/assets/132903726/46c5fd2e-75f5-4704-8e13-713bce2d793d)

### 활용 기술 / 기술적 의사 결정

| 요구사항 | 선택지 | 핵심 기술을 선택한 이유 및 근거 |
| --- | --- | --- |
| 실시간 채팅 | Web socket, Http Polling | - 서버가 클라이언트에게 비동기 메시지를 보낼 때 가장 널리 사용하는 기술입니다. 양방향 메시지 전송까지 가능합니다. |
| 부하테스트 | JMeter, nGrinder | - JMeter 선택: 소켓 통신 테스트를 위해 사용되며 다양한 플러그인과 확장 기능을 제공하여 문제 해결이나 도움을 받기 용이한 도구입니다. |
| 메세지 큐 | Kafka, RabbitMQ | - Kafka 선택: 비동기적으로 대용량의 데이터를 처리할 수 있는 메세지 큐로서 Producer와 Consumer를 가지고 있어 데이터의 정합성을 보장할 수 있습니다. 대규모 데이터 처리에 강점을 가진 기술입니다. |
| 모니터링   | Grafana, Prometheus   |- Grafana 선택: 지표를 시각화하는데 특화된 도구로서 모니터링 환경 구축에 적합합니다. 시각적으로 정보 파악이 용이하며 다양한 대시보드와 알림 설정 등의 기능을 제공합니다.|
| 배포 환경 구성  | Docker Compose, Docker Swarm, Kubernetes  |- Docker Compose 선택: 단일 호스트에서 비교적 간단한 애플리케이션 실행에 사용됩니다. Docker Swarm과 Kubernetes는 클러스터 환경에서 컨테이너 오케스트레이션과 관리를 위해 선택될 수 있는 옵션입니다. 프로젝트의 규모와 요구사항에 따라 결정되어야 합니다.

---
## ERD 🏄
<details>
    <summary>ERD 펼쳐보기</summary>
    [<img src="https://drive.google.com/file/d/1tyw0lz4LS69rVJofofqzTM7UFXcJphMI/view?usp=sharing" alt="erd">](https://drive.google.com/file/d/1tyw0lz4LS69rVJofofqzTM7UFXcJphMI/view?usp=sharing)
</details>

---
## 부하 테스트 및 성능 개선 🔥
- [🐬version 0.1]

|아키텍쳐|성능 테스트|
|---|---|
|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/5cfa0b71-0c7f-4142-a87d-371666f3058e)|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/fac33992-db00-4aa0-a82f-871f6bd657a1)|

- [🐒version 0.2]

|아키텍쳐|성능 테스트|
|---|---|
|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/db7ed7e0-616d-43fa-ad15-aba843b599ad)|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/ed60c12c-5a37-4cbc-a97d-19dd0b96d8e2)|

- [🐅version 0.3]

|아키텍쳐|성능 테스트|
|---|---|
|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/95a2d210-fd01-417a-afce-bad1db2ef325)|![image](https://github.com/innovationCamp/messenger-service/assets/132903726/de71fce0-8af1-432e-856d-ecc8bf126f86)|

---## 팀원 👨‍👩‍👦‍👦
|역할|이름|담당|github|
| --- | --- | --- | ---|
|팀장	|최선효	| 	|https://github.com/cornpip|
|팀원	|추광현	| 	|https://github.com/KH-CHOO|
|팀원	|강영준	| - 회원가입, 로그인 구현, Interceptor를 활용한 Jwt 토큰 인증  <br> - Web socket, Stomp를 활용하여 실시간 채팅 구현 <br> - 스케줄러를 이용하여 예약송금 구현 <br> - 채팅 데이터베이스 MongoDB로 변경하여 성능개선 <br> - 메세지 큐로 Kafka를 활용하여 채팅의 DB저장과 Publish 분리하여 성능개선	|https://github.com/Kkangjn|
|팀원	|한정은	| - Contact api 구현 <br> - prometheus, grafana를 활용하여 모니터링 구현 <br> - docker compose를 활용하여 배포 환경 구성 <br> - dockerfile을 활용하여 github action으로 docker image 생성 및 ci/cd 구축 <br> - jmeter 통한 websocket의 부하테스트	|hanjungeun0909 (github.com)|
