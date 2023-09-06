# 다중 객체 이미지 판별 웹 서비스

## 개발 환경
- 개발환경
    - IDE : vscode
    - Data
        - 오픈 소스 머신러닝 라이브러리 : pytorch 2.0.0
        - model : utralytics -> yolo 8
    - 개발언어
        - Python 3.9.13
        - Flask 2.3.2


## 2023/09/06
- png 파일만 보내도 json에 해당하는 정보만 전달가능
- 2차 모델은 객체의 rx,ry,rz 만 찾아서 x,y,z좌표에 해당하는 데이터셋을 만들어야함
- X와 Y(target)데이터셋을 만들어서 cnn으로 학습모델 자체적으로 만들기 예정!!
## 2023/09/05
- 크롭된것을 flask를 사용해서 백엔드에 전달 
- ply 파일 자르는 코드, x,y,z좌표 만드는 코드 등 함수로 떼어냄
- 이제 포즈 추정모델 찾아서 되는 지 확인해야함
## 2023/09/04
- segmentation 된 이미지에서 x,y의 좌표를 가져와서 ply파일에서 크롭함
- 원본이미지의 좌표들은 0~1사이로 표준화 되서 출력됨!
- ply파일을 원본 이미지의 화각으로 먼저 자름
- 자른 ply파일도 0~1사이의 좌표값으로 표준화 함
- 2d 이미지와 3d 이미지를 표현하는 구성이 다르기 때문에 1에서 좌표를 빼줘서 모양을 맞춤
## 2023/09/01
- ply크롭하고 x,y 좌표 맞추는 작업중
## 2023/08/31
- 2d 이미지와 3d 포인트 클라우드 해상도 차이가 있어서 segmatation된 좌표를 사용하는데 어려움이 있음
- 2d 이미지의 해상도는 640 * 480, 3d 이미지의 해상도는 320 * 240 
- 모델에서 출력되는 이미지는 들어간 이미지 원본의 해상도로 나옴
- 화각이 달라서 3d 이미지 내부에 2d 이미지가 들어간 모양이 됨
- 해상도 맞추는 중 ply를 크롭하면 point가 많이 사라짐 사라진 만큼 2d 이미지도 줄여야함
- 내일 세그멘테이션 되는 좌표값도 이미지를 리사이징 할때 바뀌는 지 확인해야함

## 2023/28/30
- api로 2D이미지 데이터를 받아서 모델에 넣음
- 모델에서 나오는 출력값을 json 형식으로 전처리해서 보냄
- segmentation된 이미지를 base64형식으로 인코딩 해서 json body에 보냄
- ply와 png 파일의 해상도 맞춰야함!
## 2023/08/29
- flask를 사용해서 back과 연결 확인
- back에서 받은 이미지 파일을 모델에 넣음
- 모델에서 생성된 정보 전처리해서 원하는 형태로 보낼 수 있는 것 확인
- 이제 ply 처리하는 코드 작성해야함
## 2023/08/28
- cuda로 로컬에서 gpu 사용하려고 했고 성공 (vscode가 아니라 jupyter notebook으로 해야함)
- 속도가 colab보다 느려서 포기
## 2023/08/27
- predict을 사용해서 다양한 정보 나오게 할 수 있는 것 확인
- ex) conf = 0.6 정확도 0.6이상만 나오게, save_txt = True txt파일 저장하는 파라미터 등등
## 2023/08/26
- 모델에서 segmentation된 이미지와 x,y좌표가 나오는 txt파일 확인
- 학습모델 5개 만듬
## 2023/08/25
- 학습된 모델 저장하는 법 확인 (다운로드 받고 이름명 바꾸기)
- 학습된 모델로 이미지 객체 판별가능
## 2023/08/24
- yolo8 모델로 라벨링한 데이터를 학습시킨 결과 overfitting되는 것을 확인
- 변수를 조금씩 바꾸거나 데이터셋의 양을 늘려야함
- 3d카메라를 고정시켜서 최적의 data를 뽑을 수 있는 위치를 설정해야함
## 2023/08/23
- yolo v8을 써서 다중객체를 instance segmentation 시도
- 학습을 돌리기 위해서는 이미지의 사이즈 리사이징 및 방향 조절이 필요
- roboflow라는 사이트에서 이미지에 있는 객체들을 라벨링하고 데이터 셋으로 만듬
- colab을 써서 데이터 셋을 학습시킴
## 2023/08/19
- 프로젝트 회의 및 계획서작성