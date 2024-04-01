# Map:Zero

<p align="center" ><img src= "https://github.com/team-ecolink/map2zero-backend/assets/85255237/f18c41b4-8b16-42de-91ec-0f769df2b1ed" width="400"/></p>

## 개요

**지속가능한 삶을 위한 ESG 서비스 Map:Zero**(맵투제로)의 서버 레포지토리입니다.

## 목차

#### [1. 서비스 소개](#서비스-소개)

#### [2. 기술 스택](#기술-스택)

#### [3. ERD](#erd)

#### [4. 서버 아키텍쳐](#서버-아키텍쳐)

#### [5. Swagger](#swagger)

#### [6. Github Flow 전략](#github-flow-전략)

#### [7. 컨벤션](#컨벤션)

#### [8. Contributors](#contributors)

## 서비스 소개

<!-- 담당: 최이주 -->


서울 내 다양한 지역에서 운영되고 있는
**제로 웨이스트 샵들의 위치와 리뷰, 판매 제품 정보, 매장 이용 방법,
다양한 이벤트를 한눈에 확인할 수 있는 플랫폼 서비스**<br><br>

### 🌱 일반 사용자를 위한 기능
#### 1. 내 위치 기준 가까운 매장 확인
- 스마트 서울맵 API를 이용하여 가져온 제로웨이스트샵의 정보들을 네이버 지도 API를 통해 지도 기반으로 확인할 수 있다.<br><br>
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/888b10b4-e5ce-4bfd-b5f8-2f8b5c382cf7">


#### 2. 매장 상세 정보 제공 및 리뷰, 북마크 기능
- 매장 위치, 운영 시간, 연락처, 리뷰, 판매 제품, 이벤트 정보와 같은 상세 정보를 확인할 수 있다.
- 매장 북마크 기능과 리뷰 작성 기능을 제공한다.<br><br>
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/39d559fc-679a-4512-bc44-e2046cb37be2">
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/1e0d1a2a-d469-4bde-b9e4-160dc3f81e36">
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/38756153-d56b-47d9-9cad-c187230c44fb">


#### 3. 매장 및 판매 제품 검색
- 매장 이름으로 검색할 수 있으며 판매 제품 이름 기반으로 그 제품을 파는 매장을 검색할 수 있다.
- 인기 검색어와 최근 검색어를 확인할 수 있다. <br><br>
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/8da6e211-2759-44c3-92df-b86215740cdf">
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/8959efa3-a410-4ad4-b381-0c95766c1692">
  <br>

### 🌱 점주를 위한 기능
매장 등록 신청 후 승인이 되면 매장 관리 가능
#### 1. 매장 제품 등록 및 관리
- 판매 중인 제품들을 등록할 수 있으며 재고 여부에 따라 판매중 여부를 설정할 수 있다.<br><br>
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/2050fc42-3feb-4987-ac8e-fb882fc65f35">
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/9b5f90fe-d7df-47ab-85cf-914d67d506cf">

#### 2. 매장 이벤트 등록 및 관리
- 이벤트 정보를 등록할 수 있다. <br><br>
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/c32a1f6d-a7ae-4c8f-afa7-6392bf4f0ca9">
  <img width="200" src="https://github.com/team-ecolink/map2zero-backend/assets/143402486/e53e0aa5-cb1f-464e-8aee-d7891be70a35">


<!-- 담당: 최이주 -->

## 기술 스택

<!-- 담당: 이준표 -->

#### Server
![springboot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

![SpringSecurity](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white)
#### DataBase
![mysql](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![redis](https://img.shields.io/badge/redis-%23DD0031.svg?&style=for-the-badge&logo=redis&logoColor=white)
![s3](https://img.shields.io/badge/Amazon%20S3-569A31?&style=for-the-badge&logo=AmazonS3&logoColor=white)

#### CI/CD
![gha](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

![docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

![aws](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![ec2](https://img.shields.io/badge/ec2-FF9900?style=for-the-badge&logo=amazon-ec2&logoColor=white)
![route53](https://img.shields.io/badge/Route%2053-8C4FFF?style=for-the-badge&logo=amazonroute53&logoColor=white)

![ubuntu](https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)

#### Documents
![swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)


#### ETC
![slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=white)



<!-- 담당: 이준표 -->

## ERD

<!-- 담당: 이현희 -->

![map2zero](https://github.com/team-ecolink/map2zero-backend/assets/101989268/ea3273a9-7b4a-418b-83b4-8d092d8d50b3)



<!-- 담당: 이현희 -->

## 서버 아키텍쳐

<!-- 담당: 이준표 -->

![Map2Zero-Architecture](https://github.com/team-ecolink/map2zero-backend/assets/85255237/a685d0d5-655b-4e41-8119-3c93c6c655b7)





<!-- 담당: 이준표 -->

## Swagger

<!-- 담당: 성유진 -->
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/e76d43cb-ee2b-438a-9a6a-164107deba68" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/c9032d0b-80dd-46c8-ae86-2e90cd904f5f" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/600c7111-8e78-460a-87ab-3d6ca557e6b5" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/8ba03626-03b5-4ce3-9bc2-2f5162d1d95d" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/1545898f-7e14-45e1-8edb-cce60467a3dc" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/72a3f729-38f0-401c-a4d1-de4e661da795" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/91ed3635-e4ee-4314-bc67-e5ac9fe92bff" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/14e00604-070f-4b05-94cd-4848ee92803e" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/ef215f4d-4d25-445a-b783-86b93ddfd5e7" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/c3b9c68d-91fe-490b-878a-566c0e566410" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/eb4193ce-888b-443b-a9c3-57f681fc9519" />
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/955a25e9-7bb6-4367-9f9e-3bb23ac2857e" />

<!-- 담당: 성유진 -->

## Github Flow 전략

<!-- 담당: 성유진 -->
<img src = "https://github.com/team-ecolink/map2zero-backend/assets/88930580/21eb8bd9-955c-47ad-b6c2-16062970ac94" />




<!-- 담당: 성유진 -->

## 컨벤션

<!-- 담당: 최이주 -->


`Feat` : 새로운 기능 추가<br>
`Fix` : 버그 수정<br>
`Refactor` : 코드 리팩토링<br>
`Docs` : 문서 수정<br>
`Style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우<br>
`Chore` : 빌드 업무 수정, 패키지 매니저 수정<br>


<!-- 담당: 최이주 -->

## Contributors

<!-- 담당: 이현희 -->

<table>
  <tbody>
      <td align="center"><a href="https://github.com/wnsvy607"><img src="https://github.com/team-ecolink/map2zero-backend/assets/101989268/7fcd6027-2639-44ca-ba02-e4f056293e34" width="100px;" alt=""/><br /><sub><b>BE 팀장 : 이준표 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/heegane"><img src="https://github.com/team-ecolink/map2zero-backend/assets/101989268/baefc59c-10c0-4060-a0f6-19b5015f3055" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 이현희 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/cherryiJuice"><img src="https://github.com/team-ecolink/map2zero-backend/assets/101989268/5cef55b5-18f7-4ab4-b802-c397f2c61289" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 최이주</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/uuujini"><img src="https://github.com/team-ecolink/map2zero-backend/assets/101989268/bc2b5057-6f50-4696-b32e-0d2de4230124" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 성유진</b></sub></a><br /></td>
  </tbody>
</table>



<!-- 담당: 이현희 -->
