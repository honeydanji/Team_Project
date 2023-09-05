import open3d as o3d
import numpy as np
import open3d as o3d
import os
from yaw_pitch_roll import yaw_pitch_roll_to_rotation_matrix


def cropPly(xy_list,temp_ply_path):
    # PLY 파일을 Open3D로 읽어오기
    ply_cloud = o3d.io.read_point_cloud(temp_ply_path)
    
    yaw_angle = 0  # + : crop 영역 반시계 방향 회전(카메라 시점), - : crop 영역 시계 방향 회전(카메라 시점)
    pitch_angle = 0
    roll_angle = 0
    rotation_matrix = yaw_pitch_roll_to_rotation_matrix(yaw_angle, pitch_angle, roll_angle)
    size = [0.95,  # 너비(카메라 기준, 2d width) 1.28 보다 작아지면 우측 잘리기 시작함
        0.45,  # 높이(카메라 기준, 2d height) 0.849 보다 작아지면 아래쪽 잘리기 시작함, 0.00875 = 2 pixel 거리
        1.500]  # 깊이(카메라 기준 거리, 3d depth) 1.387 보다 작아지면 좌측 아래쪽 바닥 잘리기 시작함
                # 바닥 제거 1.345

    center = [-0.015,  # + : crop 영역이 우측으로 이동, - : crop 영역이 좌측으로 이동 (-0.64551 ~ 0.69287), 합 1.33838
          0.0,  # + : crop 영역이 위쪽으로 이동, - : crop 영역이 아래쪽으로 이동 ( -0.40405 ~ 0.37817)  합 0.78222
          0.0,] # + : crop 영역이 카메라쪽으로 이동, - : crop 영역이 아래쪽으로 이동
    
    obb = o3d.geometry.OrientedBoundingBox(center, rotation_matrix, size) # or you can use axis aligned bounding box class
    
    crop_pcd0 = ply_cloud.crop(obb)
   
    
    # 포인트 클라우드 좌표 추출
    points = np.asarray(crop_pcd0.points)

    # x, y, z 좌표를 0.0에서 1.0로 스케일 및 이동
    x_min = np.min(points[:, 0])
    y_min = np.min(points[:, 1])

    x_range = np.max(points[:, 0]) - x_min
    y_range = np.max(points[:, 1]) - y_min

    scaled_x = (points[:, 0] - x_min) / x_range
    scaled_y = (points[:, 1] - y_min) / y_range

    # 변환된 좌표로 업데이트
    points[:, 0] = scaled_x
    points[:, 1] = scaled_y

    # 업데이트된 포인트 클라우드로 저장 또는 시각화
    crop_pcd0.points = o3d.utility.Vector3dVector(points)

    # 시각화 (선택 사항)
    # o3d.visualization.draw_geometries([crop_pcd])
    
    # xy_list에서 첫 번째 배열을 사용하여 새로운 배열 생성
    xy = []
    for i in range(len(xy_list)):
        new_xyz_list = np.c_[xy_list[i], np.full(xy_list[i].shape[0], 0.05)]
        xy.append(new_xyz_list)
        

    xyz_output = []
    for xyz in xy:
        corners = np.array(xyz)
        corners[:, 1] = 1 - corners[:, 1]

        # corners 배열의 데이터 유형을 float64로 변환
        bounding_polygon = corners.astype("float64")

        # SelectionPolygonVolume 생성
        vol = o3d.visualization.SelectionPolygonVolume()

        # 다각형을 어떤 축을 기준으로 정렬할 것인지 지정해야 합니다.
        # 여기서는 "Z" 축을 선택합니다. 최대값은 다각형 꼭지점 중 최대 Z 값,
        # 최소값은 다각형 꼭지점 중 최소 Z 값으로 설정합니다.
        axis_num = 2
        vol.orthogonal_axis = "Z"
        vol.axis_max = 10.0
        vol.axis_min = -10.0

        # Z 값은 설정한 vol.axis_max 및 vol.axis_min 값으로 이미 지정되었으므로
        # 모든 Z 값을 0으로 설정합니다.
        bounding_polygon[:, axis_num] = 0

        # np.array를 Vector3dVector로 변환합니다.
        vol.bounding_polygon = o3d.utility.Vector3dVector(bounding_polygon)


        # Vector3dVector를 사용하여 포인트 클라우드를 자릅니다.
        crop_pcd = vol.crop_point_cloud(crop_pcd0)

        # 시각화
        # o3d.visualization.draw_geometries([crop_pcd])
        # o3d.visualization.draw_geometries([crop_pcd, lineset])
        xyz_output.append(crop_pcd)
    os.remove(temp_ply_path)
    # points = np.asarray(xyz_output[0].points)
    # print(points)
    return xyz_output

def point_cloud_to_dict(point_cloud):
    # Extract XYZ coordinates
    points = np.asarray(point_cloud.points)
    x_coords = points[:, 0].tolist()
    y_coords = points[:, 1].tolist()
    z_coords = points[:, 2].tolist()

    # Create a dictionary for XYZ coordinates
    xyz_coordinates = {
        "x": x_coords,
        "y": y_coords,
        "z": z_coords
    }

    return xyz_coordinates