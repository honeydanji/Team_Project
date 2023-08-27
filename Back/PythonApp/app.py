from flask import Flask
from flask_restful import Api
from member import UserRegisterResource
from member import UserLoginResource
from flask_jwt_extended import JWTManager

app = Flask(__name__) 

api = Api(app)
jwt = JWTManager(app)
app.config['SECRET_KEY'] = 'your-secret-key'


# 경로와 api(리소스) 연결
api.add_resource(UserRegisterResource, '/register') # 회원가입
api.add_resource(UserLoginResource, '/login') # 로그인

if __name__ == '__main__' :
    app.run()