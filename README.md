# 다중 객체 이미지 판별 웹 서비스

## 2023/09/13
- DataResults Page
    - 서버에서 받은 데이터를 각 컴포넌트에 전달하여 차트에 가시화 완료

## 2023/09/12
- DataResults Page
    - 날짜를 뽑아내가지고 서버에 데이터 요청
    - 요청한 데이터를 각 컴포넌트에 뿌려서 차트로 가시화
- History Page
    - 이미지 캐로샐에 띄우기 완
## 2023/09/11
- Service
    - 이미지 위에 빨간 사각형을 그려서 리스트 클릭시, 리스트에 해당하는 이미지 위치에 사각형을 그리도록 코드 작성
    - 비율 안 맞는 문제, 위치를 제대로 잡을 수 없는 문제 발생
    - 비율은 해결 한 거 같은데 위치는 잡을 수 없음, 하루종일 해결 중 벋 해결 못함

## 2023/09/09
- Service  
    - 이미지 업로드 후, service page에서 사진 띄우고 클래스 뽑아서 리스트로 만들고 리스트 클릭시 6D좌표 출력 완
## 2023/09/08
- Service
    - 이미지 송부 시, 벡에서 이미지에 해당하는 6D 좌표값도 전송해줌
    - 전송된 데이터를 받아서 화면에 출력하는 중
- LoginRecoil Hook
    - nameState가 Recoil로 상태관리가 안되는 중, 이유를 찾지 못함
    - 일단 localStorage에 저장해둔 token을 사용해서 user의 name을 decoding하여 사용 중
    - login시 Email을 recoil로 저장해서 전역으로 관리하고 싶음
- Modal Component
    - 모달창에서 날짜에 따른 데이터를 송부받아 팝업창에 뿌리는 코드 작성
- History Page
    - list Component에서 서버로부터 날짜 리스트를 전달 받아 중복 날짜 제거 후, 날짜 리스트 생성
    - 만들어진 날짜 리스트를 클릭하면 서버에 해당 날짜에 해당하는 데이터를 전달 받음 -> 모달창에서 보여줄 데이터
## 2023/09/07
- List Component
    - 서버로부터 데이터를 전달 받아 props로 List Component로 전달 후 날짜를 추출하고 리스트로 만드는 작업 중
    - 잘 안되고 있음...  
- Nav Component
    - Nav dropdown bar 만들고 페이지에 넣음
    - 크기가 안 맞는 문제가 있어서 수작업으로 크기 조절 해둠
    - Nav의 팝업창이 페이지의 Components들의 아래에 위치하는 문제 발생 => Component들의 하위에 위치시키면 되긴 한데 그러면 다른 Component들 이용 불가
    - Nav를 상위에 위치시키고 방해 받지 않는 위치로 이동

## 2023/09/06
- History Page
    - list에 넣을 데이터 서버에 요청 후 response 받음
- DragDrop Page
    - 이미지 확장자에 따라 다르게 저장후, 서버에 전송
    - authorization을 header에 같이 보내지 않아 문제 발생 => header에 authorization 넣어서 문제 해결
- Login Page
    - Login 상태를 저장하기 위해서 상태 관리 라이브러리 중 Recoil 사용
    - Recoil만 사용하면 useContext가 null이라는 오류 계속 발생
    - react랑 recoil을 재다운로드를 함으로써 문제 해결

## 2023/09/05
- Login Page
    - login 하면 token 안 받아와지는 문제 발생
    - res.header['authorization'] 로 안 가져와서 문제가 발생한 거였음 소문자, 대문자 구분
- List Component
    - list 크기 조절 해결
    - className에 인라인 스타일(style={{}})을 적용하면 안되고 className 밖에 적용해야지 된다.... 바보여따

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
