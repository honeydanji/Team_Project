from datetime import timedelta

class Config :
    # 암호화 키
    JWT_SECRET_KEY = 'HASEONGJIN##DANJI' # 노출되면 절대 안된다(임의의 값)
    JWT_ACCESS_TOKEN_EXPIRES = timedelta(days=1) # 토큰 유지 시간
    PROPAGATE_EXCEPTIONS = True # 예외처리를 JWT로 처리
    
    # 파일 업로드
    TWO_UPLOAD_FOLDER = "C:/Team_Project/Back/Upload/2D"
    THREE_UPLOAD_FOLDER = "C:/Team_Project/Back/Upload/3D"
    #ALLOWED_EXTENSIONS = {'png', 'jpg', 'ply'}
    
## ignore 필수