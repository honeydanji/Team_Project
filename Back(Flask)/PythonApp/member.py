from flask import request
from flask_restful import Resource
from flask_jwt_extended import create_access_token
from datetime import datetime

# import os, sys
# sys.path.append(os.path.dirname(os.path.dirname(os.path.dirname(__file__)))) 
# sys.path.append('C:/Team_Project/Back/DB')
# memver.py 경로 == C:\Team_Project\Back\DB\mysql_connection.py

import mysql.connector
# from DB import mysql_connection
from mysql_connection import get_connection ## 경로 해결하기

from email_validator import validate_email, EmailNotValidError
# from Configuration import utils
from utils import hash_password, check_password


class UserRegisterResource(Resource) :
    def post(self) :
        data = request.get_json() # post로 회원정보 request
        
        try :
            validate_email( data['login_email'] ) # 이메일 주소 형식 확인, email_validator 사용
        except EmailNotValidError as e:
            print(str(e))
            return {'error' : str(e) }, 400

        # 비밀번호의 길이 유효 체크, 4~12자리
        if len(data['password']) < 4 or len(data['password']) > 12 :
            return { "error" : "비밀번호의 길이를 확인해주세요 (4~12자리)" }, 400
        
        # 비밀번호 확인
        if data['password'] != data['confirm_password'] :
            return { "error" : "비밀번호를 확인해주세요. "}, 400
        
        # 비밀번호 암호화, passlib 사용
        # data['password']
        hashed_password = hash_password( data['password'] )
        hashed_password_confirm = hashed_password
        # hashed_password_confirm = hash_password( data['confirm_password'])
        
        query = '''
        insert into members
            (name, login_email, password, confirm_password, phone_number, company_name, creation_date)
        values
            (%s, %s, %s, %s, %s, %s, %s)
        '''
        
        # 회원가입 생성 날짜
        current_time = datetime.now().date()

        # 회원 가입 정보 레코드
        record = ( data['name'], 
                   data['login_email'], 
                   hashed_password, 
                   hashed_password_confirm,
                   data['phone_number'],
                   data['company_name'],
                   current_time)
        connection = get_connection()
        
        try:
            cursor = connection.cursor()
            cursor.execute(query, record)
            connection.commit()
            
            # DB에 저장된 ID 열의 값 가져오기
            user_id = cursor.lastrowid
            
            cursor.close()
            connection.close()
            
            # 'user_id' JWT로 암호화
            access_token = create_access_token(user_id)
            
            return {
                "result": "success",
                "access_token": access_token,
                "hashed_password": hashed_password
            }
            
        except mysql.connector.Error as e:
            print(e)
            connection.rollback()  # 예외 발생 시 롤백하여 변경 사항 취소
            connection.close()
            return {"error": str(e)}, 503  # HTTPStatus.SERVICE_UNAVAILABLE

class UserLoginResource(Resource) :
    def post(self) : # post 
        data = request.get_json() # 데이터는 json
        connection = get_connection() # DB 연결
        
        try :
            query = '''
                        select * from members
                        where login_email = %s;
                    '''
            record = ( data['login_email'], )
            cursor = connection.cursor(dictionary=True)
            cursor.execute(query, record)
            
            result_list = cursor.fetchall()
        
            cursor.close()
            connection.close()
            
        except mysql.connector.Error as e :
            print(e)
            cursor.close()
            connection.close()
            return {"error" : str(e)}, 503
        
        # result_list = 1 : 유저 데이터 존재, 0 : 데이터 없음
        if len(result_list) != 1 :
            return {"error" : "존재하지 않는 회원입니다."}, 400
        
        # 비밀번호 확인
        user_info = result_list[0]
        check = check_password( data['password'], user_info['password'] )
        if check == False :
            return {"error" : "비밀번호가 맞지 않습니다."}, 400
        
        # 'user_id' JWT 암호화
        access_token = create_access_token(user_info['login_email'])
        
        return { "result" : "success", "access_tokken" : access_token}, 200
        