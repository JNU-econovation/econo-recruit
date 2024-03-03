# econo-recruit
![Desktop - 42](https://github.com/JNU-econovation/econo-recruit/assets/54030889/9c590bc8-2100-4bbb-97e9-f3d2df159e19)
Econovation에 오신 것을 환영합니다. 지식의 선순환을 통해 함께 성장하는 개발 동아리입니다.
위 Repository는 Econovation에서 신입모집 HR DT를 위해 만들어진 그룹웨어 Server입니다.

## Description
---
에코노베이션 동아리 신입모집 플랫폼
매 신입기수 채용시 필요한 다양한 서비스들을 통합 관리 및 지원합니다. </br>
협업을 위한 칸반보드식 업무 프로세스 지원 서비스, 지원서 작성 및 제출, TF 선정 및 전체 프로세스 관리자 페이지, 현 지원자 통계 페이지, 신입 회원 메일링, Slack Notification, 관리 모니터링 서비스를 총괄 제공하는 서비스입니다.</br>


### 👉 [Econo-Recruit 바로가기](https://recruit.econovation.kr)
현 27기 신입모집 진행중 ( 2024.03.04 ~ )
</br>
- 개인정보보호로 내부 서비스는 로그인 후 관리자에게 승인을 받으신 후에 이용이 가능합니다.


</br>
## 📚 Tech Stack

<div align="left">
<div>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/MySQL-4479A1.svg?style=flat-square&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=flat-square&logo=JSON Web Tokens&logoColor=white">
</div>

<div>
<img src="https://img.shields.io/badge/SonarCloud-F3702A?style=flat-square&logo=SonarCloud&logoColor=white">
<img src="https://img.shields.io/badge/-HURL-black?style=flat-square&logo=curl">
</div>

<div>
<img src="https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white">
</div>
</br>


#### Module
```
## 14th-team5-BE
- 🗂️ api
- 🗂️ common
- 🗂️ domain
- 🗂️ infrastructure
- 🗂️ batch

```


## 3️⃣ Project Architecture

- JVM Runtime Amazon Corretto 17
- SpringBoot 2.7.0 (Servlet MVC)
- Spring Data JPA
- vavr ( FP Tools )
- Module Architecture with Gradle Multi-Project
- Prometheus and Grafana for monitoring
- Redis for Caching
- MongoDB ( 지원서 전용 )
  
<br/>

#### Github Actions CI/CD
![Frame 1000012017](https://github.com/JNU-econovation/econo-recruit/assets/54030889/4ee424db-f00c-4918-bf83-374d95bfdcc8)


## 지원 서비스

#### 📷 Main
 ![메인화면](https://user-images.githubusercontent.com/54030889/226104495-7983e142-1150-4908-8acf-4d6d257d1089.png)
 
#### 😀 칸반보드 서비스 ( 칸반보드형 지원자 관리 플랫폼 )
- 각 칸반을 생성, 이동, 지원자 등록시 자동 카드 생성, 삭제, 네비게이션 관리, 좋아요, 댓글 기능 포함  ( 지원서 제출시 자동으로 칸반 보드 생성 )
![신입모집 칸반보드](https://user-images.githubusercontent.com/54030889/226103685-ea9ec66b-f127-413c-b78d-23aad3f49a4e.png)

#### 📱 세부 카드 관리
> 지원서 폼 내용 열람 및 평가, 댓글, 댓글 좋아요(일반 좋아요와 구분)를 통해 면접관들과 지원자들에 대해 소통할 수 있습니다.
![카드내용](https://user-images.githubusercontent.com/54030889/226103761-bb1bb54e-fada-446d-bd07-d55b45770935.png)

### 3. 관리자 페이지
- 신입모집 TF 선정 , 통계 열람
![신입모집 관리자 페이지](https://user-images.githubusercontent.com/54030889/226104145-91f758e8-1d44-44b9-b984-af77a9113641.png)
![신입모집 관리자 페이지통계](https://user-images.githubusercontent.com/54030889/226104347-0b71bfae-866b-4724-8231-59fbb17eff50.png)
![관리자 페이지](https://user-images.githubusercontent.com/54030889/226104472-f1497958-9b78-4acd-bd96-ada15df9cfbc.png)

#### 📱 지원서 신청
Json 파일 구조에 따라 자동으로 UI 생성 및 관리되게 세팅되었습니다.
![신청](https://user-images.githubusercontent.com/54030889/226104521-7806fffa-634c-48b5-95a7-87d66e7bfbb5.png)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=JNU-econovation_econo-recruit)
