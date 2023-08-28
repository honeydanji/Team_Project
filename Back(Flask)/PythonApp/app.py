from flask import Flask
from flask_restful import Api
from member import UserRegisterResource, UserLoginResource
from Upload import MultiFileUpload
from flask_jwt_extended import JWTManager
from flask_cors import CORS  # CORS 패키지 추가

import config

app = Flask(__name__) 

api = Api(app) # 경로와 api(리소스) 연결
CORS(app) # CORS 설정: 모든 도메인에서의 요청을 허용
jwt = JWTManager(app) # jwt 인증 방식 추가

# jwt
app.config['JWT_SECRET_KEY'] = config.Config.JWT_SECRET_KEY 
app.config['JWT_ACCESS_TOKEN_EXPIRES'] = config.Config.JWT_ACCESS_TOKEN_EXPIRES

# file_upload
app.config['TWO_UPLOAD_FOLDER'] = config.Config.TWO_UPLOAD_FOLDER
app.config['THREE_UPLOAD_FOLDER'] = config.Config.THREE_UPLOAD_FOLDER

# RestAPI
api.add_resource(UserRegisterResource, '/register') # 회원가입
api.add_resource(UserLoginResource, '/login') # 로그인
api.add_resource(MultiFileUpload, '/upload') # 2D,3D 업로드

if __name__ == '__main__' :
    app.run(host='0.0.0.0', port=80, debug=True)