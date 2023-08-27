from flask import request
from flask_restful import Resource
from flask_jwt_extended import create_access_token

import mysql.connector
from mysql_connection import get_connection ## 경로 해결하기

from email_validator import validate_email, EmailNotValidError
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
            (name, login_email, password, confirm_password, phone_number, company_name)
        values
            (%s, %s, %s, %s, %s, %s)
        '''
        
        record = ( data['name'], 
                   data['login_email'], 
                   hashed_password, 
                   hashed_password_confirm,
                   data['phone_number'],
                   data['company_name'])
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
            # i = 0
            # for record in result_list :
            #     result_list[i]['created_at'] = record['created_at'].isoformat()
            #     result_list[i]['updated_at'] = record['updated_at'].isoformat()
            #     i += 1
            
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
        access_token = create_access_token(user_info['id'])
        
        return { "result" : "success", "access_tokken" : access_token}, 200
        