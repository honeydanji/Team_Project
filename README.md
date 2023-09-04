# 다중 객체 이미지 판별 웹 서비스

## 2023/09/04
- Modals Component
    - History Page의 날짜 클릭 시 사용할 모달 창 1차 뼈대 완성
    - 모달 창에 띄워둔 carousel의 크기를 줄어야 하는데 줄여지지 않는 문제 발생 width는 줄여지는데, height는 크기 조절이 이상함
    - height의 크기가 작아지면 모달 창 안의 div들의 배열이 이상해지는 문제 有
- Login Page
    - 회원가입 후, 회원가입 정보를 바탕으로 백에서 토큰을 받아 로그인을 하는 코드 생성
    - post로 주고 받고, 잘 작동 되는 것을 확인 완
- List Component
    - 1차로 생성해둔 List Component에 스크롤바를 생성 함.
    - History Page에 Component를 끼워보니 크키가 너무 큰 문제가 발생
    - 바라는 크기로 조절 불가. 너무 크거나 작게만 설정되는 문제 발생. 해결 중

## 2023/09/01
- History Page
    - fake data를 넣은 History page 1차 뼈대 완성
- Service Page
    - DragDrop에서 업로드한 이미지를 모델돌려서 segmentaion된 이미지를 받아서 화면에 띄우는 코드 작성
    - DragDrop에서 클릭을 하면 이미지를 업로드하고 서버에서 url을 응답을 받아, 전달 받은 url을 Service로 넘겨 띄우려고 구상
    - 페이지를 넘김과 동시에 url을 파라미터로 넘기는 방식 사용. useNavigation으로 페이지와 파라미터를 함께 넘기고 Service Page에서 useLocation으로 파라미터를 받음
    - url이 잘 받아지다가 안 받아지는 문제가 발생, 계속해서 null값이 뜨는 문제가 발생
    - 'uploadImageUrl' 상태 업데이트가 문제 인 것으로 보여 'useEffect'를 활용하여 해결 시도
    - 시도 결과, 서버와의 통신 시간 때문에 uploadImageUrl이 다시 업데이트 되기 전에 다음 코드를 실행하려고 하다보니 null으로 처리되는 문제인 것 같았음
    - useEffect를 이용하여 uploadImageUrl의 상태 업데이트가 완료 된 후 필요한 동작을 수행하도록 변경
    - null 값이 아닌 url이 계속 잘 들어오도록 해결 완료

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
