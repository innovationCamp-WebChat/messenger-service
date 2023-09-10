
https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/86f7ce8b-0e9a-4e45-9331-63e48533cd7c
![image](https://github.com/innovationCamp/messenger-service/assets/132903726/65141935-2590-4aaf-98b3-561a88d44bf4)

# WebChat
### 대규모 트래픽 처리가 가능한 실시간 채팅 서비스
- 180개 국가에서 대규모의 사람이 이용하는 WhatsApp채팅 앱 서비스를 오마주한 채팅 서비스
- 현대 사회에 필수불가결한 메신저 어플의 대용량 데이터 발생과 이로 인한 부하를 견뎌내기 위해 필요한 대책을 직접 리서치 및 적용

[WebChat.pdf](https://github.com/innovationCamp/messenger-service/files/12566998/WebChat.pdf)
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

### 4. 채팅 검색 기능
- 검색 성능 개선

---
## 영상
- [최종 발표 영상](https://youtu.be/5LnQwj8_g30)
- [간단 홍보 영상](https://youtu.be/uscni2WGS4U)
- 핵심 기능 시연 영상


| 채팅 | 전자지갑 |
|---|---|
|<img src="![채팅시연-_online-video-cutter com_](https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/359d9fff-f095-40bc-8477-423a6797acc3)
" alt="채팅시연" width="450" height="450"> | <img src="![전자지갑시연-_online-video-cutter com_](https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/60081942-aa29-40bd-aa87-30ec767617f3)
" alt="전자지갑 시연" width="450" height="450"> |

| Jmeter 테스트 | 모니터링 |
|---|---|
|<img src="![jmeter-레포트-_online-video-cutter com_](https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/57300f9d-79fe-48b2-a028-1cdc5a5375bf)
"alt ="Jmeter 시연 "width =" 450 "height =" 450 "> | <img src = "![그라파나-_online-video-cutter com_ (1)](https://github.com/innovationCamp-WebChat/messenger-service/assets/132903726/97956b46-bebc-49ca-8d11-73e8fae72674)
"alt ="모니터링 시연 "width =" 450 "height =" 450 "> |

---
## 서비스 아키텍처 ⚙️
![image](https://github.com/innovationCamp/messenger-service/assets/132903726/46c5fd2e-75f5-4704-8e13-713bce2d793d)

### 활용 기술 / 기술적 의사 결정

| 요구사항 | 선택지 | 핵심 기술을 선택한 이유 및 근거  |
| --- | --- | --- |
| 실시간 채팅 | Web socket,Http Polling | - 서버가 클라이언트에게 비동기 메시지를 보낼 때 가장 널리 사용하는 기술- 양방향 메시지 전송까지 가능 |
| 부하테스트 | jmeter, ngrinder | - jmeter선택- 소켓 통신 테스트를 위해- jmeter는 다양한 플러그인과 확장기능을 제공,   많은 사용자들에게 알려진 도구로서 문제 해결이나   도움을 받기 용이 |
| 메세지 큐 | kafka, rabbitMQ | - 비동기적으로 DB에 저장할 수 있고 정합성을 보장- Producer와 Consumer를 가지고 대용량의 데이터를 처리하는데 강점을 가지는 kafka 선택 |
| 모니터링 | grafana,prometheus | - grafana : 지표를 시각화 하는데 특화 |
| 배포 환경 구성 | docker compose,docker swarm,kubernetes | - Compose 파일에 정의된 서비스들을 한 번에 실행할 수 있으며, 단일 호스트와 비교적 간단한 애플리케이션인 경우 충분하다고 판단 |
| 데이터베이스 | MySQL, MongoDB | - 데이터의 원자성이 보장되는 관계형데이터베이스 중 익숙한 MySQL로 구현- 수정할 일이 없고 속도가 중요한 채팅에서 NoSQL이 강점을 가진다 생각하여 채팅만 MongoDB로 변경 |
| CI/CD | Github Action,Jenkins | - 클라우드에서 동작하므로 어떤 설치도 필요 없다. 모든 GitHub 이벤트에 대해 GitHub Actions를 제공하고 있다. GitHub에 push, PR 이벤트가 발생할 때 자동 테스트, 배포가 쉽게 이루어지기 때문에 개발에 몰두할 수 있음
- git action 은 yaml 기반으로 위크플로우로 정의하여 쉽게 구성 가능
- jenkins는 ci/cd 파이프라인을 유연하게 구축가능 |


---
## ERD 🏄
<details>
    <summary>ERD 펼쳐보기</summary>
    <img src="https://drive.google.com/file/d/1tyw0lz4LS69rVJofofqzTM7UFXcJphMI/view?usp=sharing" alt="erd">
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
|팀원	|강영준	| 	|https://github.com/Kkangjn|
|팀원	|한정은	| 	|hanjungeun0909 (github.com)|
