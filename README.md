# 다중 객체 이미지 판별 웹 서비스

## Development environment
- IDE : vscode
- Server
  - SprinBoot 3.1.3
    - jdk 17
    - port 8080
  - 외부 API
    - Python 3.9.13
    - Flask 2.3.2
    - port 5000

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
<details>
    <summary> DB 설계도  </summary>
    <img src="https://github.com/honeydanji/Team_Project/assets/129818881/4b86ac13-a64f-40b4-a4da-5226c2ef80fa"/>
</details>


## ALL Programing
### Flask와 MySQL 연동(2023/08/25)
>>
### 회원가입 및 로그인 구현 (2023/08/25)
>>


