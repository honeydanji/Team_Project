from flask import Flask, request, jsonify
from ultralytics import YOLO
from PIL import Image

app = Flask(__name__)

model = YOLO("model_v3.pt")

@app.route("/predict", methods=["POST"])
def home():
    if "image" not in request.files:
        return "No image part"

    image = request.files["image"]
    if image.filename == "":
        return "No selected file"

    img = Image.open(image)
    
    # 모델로 이미지 예측을 수행합니다.
    pred_results = model.predict(source=img, save=True, conf=0.6, save_txt=True, device="cpu", project="results", name="jwj")
   
    


    # 결과가 리스트이므로 첫 번째 요소에서 예측 결과를 추출하여 JSON으로 가공합니다.
    if pred_results and len(pred_results) > 0:
        pred_result = pred_results[0]
        predictions = []
        for pred in pred_result.pred[0]:
            label, confidence, box = pred.tolist()
            predictions.append({"label": label, "confidence": confidence, "box": box})
        
        return jsonify({"predictions": predictions})
    else:
        return "No predictions available"

if __name__ == "__main__":
    app.run(debug=True)
