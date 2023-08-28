from flask import request ## 클라리언트로부터 데이터를 요청받는 함수
from flask import jsonify ## 파이썬의 데이터를 json 형식으로 변환해서 응답하는 함수.
from flask import current_app ## 사용 중인 Flask 앱 객체 접근코드
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity, get_jwt
from werkzeug.utils import secure_filename
import os
from mysql_connection import get_connection ## 경로 해결하기

 
class MultiFileUpload(Resource) :
    @jwt_required() # 로그인 유저(Token check)
    def post(self) :
        
        # 로그인한 사용자 식별 정보
        current_user = get_jwt_identity()
        print(current_user)
        
        # 로그인 권한 check
        if current_user is None:
            return jsonify({"message" : "로그인이 필요합니다."})
        
        # 토큰에서 권한 정보 추출
        jwt_data = get_jwt()
        user_roles = jwt_data.get('role', [])
        
        print(user_roles)
        
        # 권한이 있으면 여기
        if "ROLE_MEMBER" in user_roles:
            png_file = request.files['png_file'] # png 파일 request
            ply_file = request.files['ply_file'] # fly 파일 request
                        
            # request 한 file 지정 경로로 저장.
            if ply_file and png_file :
                ply_filename = secure_filename(ply_file.filename)
                png_filename = secure_filename(png_file.filename)

                png_file.save(os.path.join(current_app.config['TWO_UPLOAD_FOLDER'], png_filename))
                ply_file.save(os.path.join(current_app.config['THREE_UPLOAD_FOLDER'], ply_filename))
                  
            # two_query = '''
            # insert into 2D_original_image
            #     (2d_original_path)
            # values
            #     (%s)
            # '''
            # connection = get_connection()
            
            # try:
            #     cursor = connection.cursor()
            #     cursor.excute(two_query, )
            
            
            # three_query = '''
            # insert into 3D_original_point_cloud
            #     (3d_original_path)
            # values
            #     (%s)
            # '''
        
            return jsonify({"message":"이미지 업로드에 성공했습니다."}), 200
        # 권한이 없으면 여기로
        else :
            return jsonify({"message": "이미지 업로드 권한이 없습니다."})
        
        