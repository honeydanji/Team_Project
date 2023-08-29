from flask import Flask, request, jsonify
from ultralytics import YOLO
from PIL import Image
from flask_cors import CORS
import numpy as np
import os
from werkzeug.utils import secure_filename

app = Flask(__name__)
CORS(app)

# 모델 초기화
model = YOLO("model_v3.pt")

@app.route("/uploadFlask", methods=["POST"])
def home():
    try:
        if "imageFile" not in request.files:
            return "No image part"

        image = request.files["imageFile"]
        if image.filename == "":
            return "No selected file"
                
        img = Image.open(image)
        # 이미지를 임시 파일로 저장합니다.
        temp_image_path = "temp_image.jpg"
        img.save(temp_image_path)
        # 모델로 이미지 예측 수행
        # results = model(temp_image_path, conf=0.6, save_txt=True, device="cpu",save=True, project="results",name="output")/
        results = model(temp_image_path, conf=0.6, device="cpu",project="results",name="output")
        
         # 임시 이미지 파일을 삭제합니다.
        os.remove(temp_image_path)
        
        response_data = []  # 응답 데이터를 담을 리스트 초기화

        for result in results:
            # 탐지된 객체 정보 추출
            # 모두 tesor 타입이라서 json에서 읽을 수 있는 형태로 전처리 해야함
            class_name = result.boxes.cls.tolist()
            accuracy = result.boxes.conf.tolist()
            xy_list = result.masks.xyn  
        
        
        for index, (coords, class_val, acc_val) in enumerate(zip(xy_list, class_name, accuracy)):
            x_coords = [coord[0].item() for coord in coords]
            y_coords = [coord[1].item() for coord in coords]
    
            print(f"Object {index} - Class: {class_val}, Accuracy: {acc_val}, x coordinates: {x_coords} ,y coordinates: {y_coords}")
            
            # 응답 데이터를 딕셔너리로 구성하여 리스트에 추가
            object_data = {
                "Object_num" : index,
                "class_name": class_val,
                "accuracy": acc_val,
                "x_coordinates": x_coords,
                "y_coordinates": y_coords
            }
            response_data.append(object_data)
           

        # JSON 형식으로 응답 데이터 전송
        return jsonify(response_data)

    except Exception as e:
        # 예외가 발생한 경우에 실행될 코드
        app.logger.error(f"Error: {str(e)}")
        return f"Error: {str(e)}"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80, debug=True)
