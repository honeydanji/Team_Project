# Flask 애플리케이션 스크립트

from flask import Flask, request, jsonify
from flask_cors import CORS
from predict_segmentation import predict_objects  # object_detection.py 파일에서 predict_objects 함수 임포트

app = Flask(__name__)
CORS(app)

@app.route("/uploadFlask", methods=["POST"])
def home():
    try:
        if "pngFile" not in request.files:
            return "No image part"

        image = request.files["pngFile"]
        if image.filename == "":
            return "No selected file"
        
        if "plyFile" not in request.files:
            return "No image part"

        ply = request.files["plyFile"]
        # if ply.filename == "":
        #     return "No selected file"
        
        
        print(image.filename)
        print(ply.filename)

        # predict_objects 함수 호출하여 객체 탐지 수행
        response_data = predict_objects(image,ply)

        # JSON 형식으로 응답 데이터 전송
        return jsonify(response_data)

    except Exception as e:
        app.logger.error(f"Error: {str(e)}")
        return f"Error: {str(e)}"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80, debug=True)