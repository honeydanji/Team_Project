# object_detection.py

from PIL import Image
import os
import base64
from io import BytesIO
from ultralytics import YOLO
from crop_ply import *
import open3d as o3d

# 모델 초기화
model = YOLO("models/model_v3.pt")

def predict_objects(image,ply_file):
    try:
        img = Image.open(image)
        # 이미지를 임시 파일로 저장합니다.
        temp_image_path = f"{image.filename}.jpg"
        img.save(temp_image_path, format="JPEG")
        # 모델로 이미지 예측 수행
        results = model(temp_image_path, conf=0.6, device="cpu", project="results", name="output")
        # 임시 이미지 파일을 삭제합니다.
        os.remove(temp_image_path)

     # PLY 파일을 업로드한 후 Open3D로 사용하기
        ply = ply_file.read()  # PLY 파일을 바이너리 데이터로 읽기
        with open("temp.ply", "wb") as temp_ply:
            temp_ply.write(ply)
       
        # # PLY 파일을 읽어 PointCloud로 변환
        # point_cloud = o3d.io.read_point_cloud("temp.ply")
        # o3d.io.write_point_cloud("output.ply", point_cloud)

       

        response_data = []  # 응답 데이터를 담을 리스트 초기화

        for result in results:
            im_array = result.plot()  # plot a RGB numpy array of predictions
            im = Image.fromarray(im_array[..., ::-1])  # RGB PIL image

            # Encode the image as Base64
            buffered = BytesIO()
            im.save(buffered, format="jpeg")
            encoded_image = base64.b64encode(buffered.getvalue()).decode("utf-8")
            data_uri = f"{encoded_image}"

            # 이미지 데이터를 딕셔너리에 추가
            object_data = {
                "image_name": f"{image.filename.split('.')[0]}.jpg",
                "ply_name" : f"{ply_file.filename}",
                "image": data_uri,
                "detections": []
            }

            # 탐지된 객체 정보 추출
            class_name = result.boxes.cls.tolist()
            accuracy = result.boxes.conf.tolist()
            boxing = result.boxes.xywh.tolist()
            xy_list = result.masks.xyn
            
            #xy좌표를 이용해 ply자르기
            xyz_output = cropPly(xy_list,"temp.ply")
            xyz_data_list = [point_cloud_to_dict(pc) for pc in xyz_output]
            print(len(xyz_data_list))
           

            for index, (coords, class_val, acc_val, box_info,points) in enumerate(zip(xy_list, class_name, accuracy, boxing,xyz_data_list)):
                x_coords = [coord[0].item() for coord in coords]
                y_coords = [coord[1].item() for coord in coords]
            
                x_point = points["x"]
                y_point = points["y"]
                z_point = points["z"]

                # 응답 데이터를 딕셔너리로 구성하여 리스트에 추가
                detection = {
                    "Object_num": index,
                    "class_name": class_val,
                    "accuracy": acc_val,
                    "x_coordinates": x_coords,
                    "y_coordinates": y_coords,
                    "x_point" : x_point,
                    "y_point" : y_point, 
                    "z_point" : z_point,  
                    "box_info": box_info
                }

                object_data["detections"].append(detection)

            response_data.append(object_data)

        return response_data

    except Exception as e:
        return str(e)