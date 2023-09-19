# 다중 객체 이미지 판별 웹 서비스

<details>
<summary><b>목표</b></summary>
- 다중 객체 이미지를 활용하여 객체의 포즈를 추정하는 솔루션 개발
</details>

<details>
<summary><b>필요성</b></summary>
- 이미지 처리와 컴퓨터 비전 분야 응용 가능
- 빈피킹 작업에 사용될 정확한 객체 6D 포즈를 추정할 수 있다면 자동화된 로봇이나 시스템을 통해 효율적으로 작업을 처리할 수 있음
</details>

<details>
<summary><b>목적</b></summary>
- 시현용 홈페이지를 제작하여 사용자가 객체 포즈 추정 과정을 시각적으로 확인할 수 있도록 함
</details>

<img src="https://github.com/honeydanji/Team_Project/assets/129818881/f98f0ae1-0b64-4407-aa93-cde92a6e030f">


## 프로젝트 구성도
<img src='https://github.com/honeydanji/Team_Project/assets/129818881/4c5f021e-4c82-488e-9113-6eaf403fa349'>

## 2023/08/19
- 프로젝트 회의 및 계획서작성

## 2023/08/21
- 프로젝트 계획서 1차 작성 및 제출

## 2023/08/22
- 프로젝트 계획서 2차 수정 및 제출
- 업체로부터 RGB 카메라 받음

## 2023/08/23
- 프로젝트 계획서 3차 수정
- inte D415 카메라 사용법 숙지
  - (1) 카메라 고정
  - (2) 2D, 3D(Point Cloud) 100% 동일한 환경에서 촬영
- Data
  - 데이터 라벨링 작업
  - 객체분할 모델 기술 1차 검증
- Front
  - 서비스화면 설계
- Back
  - DB EER 설계

## 2023/08/24
- 프로젝트 계획서 4차 수정 및 제출
- 서비스화면 설계(2차 수정)
  <details>
    <img src='https://github.com/honeydanji/Team_Project/assets/129818881/fe846c34-77f5-4dde-8ad6-0ced7d3701f3'/>
  </details>
- DB 설계(2차 수정)
  <details>
     <img src="https://github.com/honeydanji/Team_Project/assets/129818881/4b86ac13-a64f-40b4-a4da-5226c2ef80fa"/>  
  </details>
 

## 2023/08/25
- 프로젝트 2차 회의
  - Front :
    - 기존 : 결과물을 단순히 수치로 시각화 함.
    - 수정 : 핵심 결과물에 대한 시각적 요소와 서비스 구현의 목적성을 잘 나타낼 수 있도록 서비스화면 보안
  - Data :
    - 기존 : 과자 패키지 모양에 따른 객체 데이터셋 선정
    - 수정 : 특정 서비스 이용자(공구, 재활용품 등)에 맞는 객체 데이터셋 선정
- 서비스화면 설계(3차 수정)
  <details>
    <summary>추가페이지 이미지</summary>
    <img src='https://github.com/honeydanji/Team_Project/assets/129818936/276adf8c-1114-4e2e-9f4d-7854afe6214c'/>
  </details>

## 2023/08/28
- 프로젝트 3차 회의
  - Back :
    - 기존 : Flask로 서버 구현
    - 수정 : StringBoot와 Flask 동시 사용. StringBoot는 클라이언트로 부터 직접 request, response 하고 flask는 ai 모델을 위한 데이터 전처리용으로 사용

## 2023/08/29
- 클라이언트, server(SpringBoot), 외부api(Flask) 이미지데이터 통신(Request) 성공

## 2023/08/30
- 클라이언트, server(SpringBoot), 외부api(Flask) 이미지데이터 통신(Request, Response) 성공
- 입력 : 2D, 3D(Pointcloud)
- 출력 : 2D에 대한 segmentation 이미지
<div style="display: flex; flex-direction: row;">
  <img src="https://github.com/honeydanji/Team_Project/assets/129818881/63269691-002d-4292-a185-1bd748843e6b" alt="Image 1", style="width: 45%; style="margin-right: 5px;">
  <img src="https://github.com/honeydanji/Team_Project/assets/129818881/367a7b9a-e02e-4f3e-bc8b-c649cb43ac83" alt="Image 2", style="width: 45%;">
</div>

## 2023/09/01
- (생략)

## 팀원 
|<img width="200" alt="image" src="https://avatars.githubusercontent.com/u/129818881?v=4">|<img width="200" alt="image" src="https://avatars.githubusercontent.com/u/129818936?v=4">|<img width="200" alt="image" src="https://avatars.githubusercontent.com/u/129819084?v=4">|
| :---------------------------------: | :-----------------------------------:|:-----------------------------------:|
|              Back                   |           Front                      |                Data                 |
|             하성진                   |          박수현                      |                  조원준              |      
| [GitHub](https://github.com/honeydanji/)  | [GitHub](https://github.com/SuHyunParkSunshine/)  | [GitHub](https://github.com/jwjb1020/)  
