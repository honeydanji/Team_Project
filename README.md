# 다중 객체 이미지 판별 웹 서비스

## Development environment
- IDE : VScode
<div>
  <img src="https://img.shields.io/badge/java 17-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/springboot 3.1.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
  <img src="https://img.shields.io/badge/springsecurity 6.1.1-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql 8.0.32-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
</div>

## 핵심기술
- Spring MVC 아키텍처 기반 구현
- RestAPI 아키텍처 기반 설계
- JWT 토큰을 사용하여 인증 처리
- 웹서버(SpringBoot)와 서버 API(Flask) 통신을 위해 RestTemplate 사용
   
## DatabaseModel
<img src = "https://github.com/honeydanji/Team_Project/assets/129818881/bc9a2438-cecf-497e-8e57-855483f777ce">

## Restful API 명세
<img src = "https://github.com/honeydanji/Team_Project/assets/129818881/ac3c652b-1615-489f-81e8-ac3d2763323a">

## 2023/09/08
- git 명령어 정리
- DTO를 통한 JPQL 사용
- FlaskResponse 데이터 전처리 및 DB 저장

## 2023/09/07
- DB 컬럼 이름 수정
- comments엔티티 put, delete 추가 및 트랜잭션 설정
- view 생성
  - 기존 : jpql 5개 사용해서 테이블마다 컬럼 1개씩 리턴후 리스트형태로 반환
  - 수정 : DB 에서 view 생성후 쿼리메소드 단독 사용 (코드최적화)
- 6D 데이터 전처리 (org.apache.commons 사용)

## 2023/09/06
- FlaskResponse 데이터 전처리 및 DB 저장
- jwt/Filter 코드 수정

## 2023/09/05
- FlaskResponse 데이터 전처리 및 DB 저장
- JPQL 코드 추가 

## 2023/09/04
- JWT 로그인 구현
- RESTAPI 권한 부여
- member 및 history 1:N 맵핑

## 2023/09/01
- DB 컬럼 추가
  - 객체탐지 영역 좌표, width, height
- Service
  - @Transaction 추가

## 2023/08/31
- client와 server 통신(Request, Response) 성공
- 모델링 변형
  - 기존 : Service <-> Entity <-> Repository
  - 수정 : Service <-> (DTO) <-> Entity <-> Repositroy 

## 2023/08/30
- 외부 API 통신
  - 응답 데이터(Json) 파싱 후 DB 저장
  - 인코딩된 데이터(Base64) 디코딩 작업 실시
- Code 수정
  - DI변경(필드 주입 > 생성자 주입) 
  - 이미지 확장자에 따른 localpath 변경
- 전체 통신
  - 클라이언트 <-> server <-> 외부 API
     
## 2023/08/29
- CORS 정책 설정
  - 중복되는 코드가 있어 수정이 코드 최적화 작업이 필요함.
- 데이터 Post, Get 테스트
  - 현재 외부 API 코드가 구현되지 않아 포스트맨을 이용함.
  - 이미지 데이터 POST > 원본데이터 local저장 > DB 이미지 URL(Get) 저장 성공
- 외부 API 통신 성공
  - RestTemplate 사용 (Spring 3)
  - 응답은 Json형태 

## 2023/08/28
- 기존 Flask 서버에서 SpringBoot 서버 추가
    - Flask : AI 모델 및 데이터 전처리
    - SpringBoot : 클라이언트와 직접 상호작용     
- RESTAPI 명세 작성
- 클라이언트와 서버 (SpringBoot, Flask) 구조 시각화

## 2023/08/25
- REST API 설계
        
## 2023/08/24
- DB 설계
