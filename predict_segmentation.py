# object_detection.py

from PIL import Image
import os
import base64
from io import BytesIO
from ultralytics import YOLO
from crop_ply import *
import open3d as o3d
from output6d import *

# 모델 초기화
model = YOLO("modelFolder/model_v7.pt")

def predict_objects(image, ply_file):
    try:
        img = Image.open(image)
        # 이미지를 임시 파일로 저장합니다.
        temp_image_path = f"{image.filename.split('.')[0]}.jpg"
        img.save(temp_image_path, format="JPEG")
        # 모델로 이미지 예측 수행
        results = model(temp_image_path, conf=0.5, device="cpu", project="results", name="output")
        # 임시 이미지 파일을 삭제합니다.
        os.remove(temp_image_path)

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
                "image": data_uri,
                "detections": []
            }

            # 탐지된 객체 정보 추출
            class_name = result.boxes.cls.tolist()
            accuracy = result.boxes.conf.tolist()
            boxing = result.boxes.xywh.tolist()
            xy_list = result.masks.xyn

          
            

            for index, (coords, class_val, acc_val, box_info) in enumerate(zip(xy_list, class_name, accuracy, boxing)):
                x_coords = [coord[0].item() for coord in coords]
                y_coords = [coord[1].item() for coord in coords]

                # 응답 데이터를 딕셔너리로 구성하여 리스트에 추가
                detection = {
                    "Object_num": index,
                    "class_name": class_val,
                    "accuracy": acc_val,
                    "x_coordinates": x_coords,
                    "y_coordinates": y_coords,
                    "box_info": box_info
                }

                object_data["detections"].append(detection)

            response_data.append(object_data)

        if ply_file:
            # Ply 파일이 있는 경우에만 아래 코드 블록 실행
            try:
                # PLY 파일을 업로드한 후 Open3D로 사용하기
                ply = ply_file.read()  # PLY 파일을 바이너리 데이터로 읽기
                with open("temp.ply", "wb") as temp_ply:
                    temp_ply.write(ply)
        
               # point_cloud를 사용하여 원하는 작업 수행
                xyz_output = cropPly(xy_list, "temp.ply")
                
                # output6d함수를 이용해 6d좌표 구하기
                list6d = outputreal6d(xyz_output) 
                print(list6d)
                for index,list6d_info in enumerate(list6d):
                    object_data["detections"][index]["6dpose"] = list6d_info
                    # object_data["detections"][index]["y_center"] = list6d_info[1]
                    # object_data["detections"][index]["z_center"] = list6d_info[2]
                    # object_data["detections"][index]["rx"] = list6d_info[3]
                    # object_data["detections"][index]["ry"] = list6d_info[4]
                    # object_data["detections"][index]["rz"] = list6d_info[5]
                
                
                xyz_data_list = [point_cloud_to_dict(pc) for pc in xyz_output]

                for index, points in enumerate(xyz_data_list):
                    x_point = points["x"]
                    y_point = points["y"]
                    z_point = points["z"]
                    print(len(x_point))
                    # # XYZ 좌표 데이터를 응답 데이터에 추가
                    object_data["detections"][index]["x_point"] = x_point
                    object_data["detections"][index]["y_point"] = y_point
                    object_data["detections"][index]["z_point"] = z_point

                # 예를 들어, point_cloud 정보를 JSON 데이터에 추가
                object_data["ply_name"] = f"{ply_file.filename}"
                    
                
                

            except Exception as e:
                return str(e)

        return response_data

    except Exception as e:
        return str(e)
