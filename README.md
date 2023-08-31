# 다중 객체 이미지 판별 웹 서비스
## 2023/08/31
- Service Page
    - Back에서 처리한 이미지 데이터를 받기위해 fetch 시도중
    - uploadSpring으로 font단에서 request하면 Back단에서 response로 이미지 URL을 전달해주는 방식 시도 중
- DataResults Page
    - fake data를 넣은 차트들을 배치한 DataResults Page 1차 뼈대 완성
    - 추후 Back에서 데이터를 전달 받아서 이미지에 따라 변하는 차트로 변경 예정
- ChartPie Component
    - fake data를 넣은 component 1차완성
    - 추후 Back에서 데이터를 전달 받아서 웹에 띄우는 코드 작성 예정
## 2023/08/30
- ChartBar Component
    - fake data를 넣은 component 1차완성
    - 추후 퍼센트 계산식을 넣어서 퍼센트가 들어오는 값에 따라 유동적으로 변경 될 수 있도록 해야 함

- Data Results
    - 페이지 framework 구축 중: header, logo 등등
    - js와 css 사용하여 처음부터 하나하나 설계하는 중

- Service
    - 업로드한 이미지에 대한 결과값을 띄워주는 페이지 구축 중
    - 이미지 박스, 결과값(좌표값, 6D좌표값), 클래스 리스트 나타나는 화면 뼈대(framework) 구현

## 2023/08/29
- DragDrop
    - 업로드한 이미지를 Back-end에서 DB에서 저장할 수 있도록 서버로 전송하는 코드 작성
- Stats Component : tailwind 사용하여 Component 생성
- Data Results : tailwind 사용하여 Page 생성
- ChartBar Component: js와 css를 이용하여 animation이 들어가 있는 horizontal ChartBar 생성

## 2023/08/28
- Sign up 시
    - RestAPI로 Back-end단에 정보 전달 후 DB 저장하기 위해 클라이언트로부터 정보 받은 후 전달하는 코드 작성
    - cors 연결에러 해결 후 연결 되는 것 확인 완

- Carousel Component 생성
    - 사진 이미지를 받아 슬라이드로 표현할 수 있도록 Carousel Component 생성

## 2023/08/25
- UI 설계(3차)
    <details>
    <summary>추가페이지 이미지</summary>
    <img src='https://github.com/honeydanji/Team_Project/assets/129818936/276adf8c-1114-4e2e-9f4d-7854afe6214c'/>
    </details>
    - 교수님 피드백 후, 회사 측에서 필요한 데이터를 화면에 표시하는 페이지를 추가로 설계
- Signin & Signup 페이지 구현

## 2023/08/24
<details>
    <summary> UI 설계(2차) </summary>
    <img src='https://github.com/honeydanji/Team_Project/assets/129818881/fe846c34-77f5-4dde-8ad6-0ced7d3701f3'/>
</details>

## 2023/08/21
- 개발환경 셋팅
    - IDE : vscode
    - React : ^18.2.0
    - Node.js : v18.17.0
    - npm version : 9.6.7
    - swiper version : 9.3.2 
- UI 설계 프로그램
    - Figma

## 2023/08/19
- 프로젝트 회의 및 계획서작성
    - 프로젝트 계획서(230825)
