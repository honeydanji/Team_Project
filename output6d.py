import open3d as o3d
import numpy as np
from calculate_rotation_angles import *

#6d 정보 추출하는 함수
def output6d(xyz_list):
    list6d = []
    for cloud in xyz_list:
         # 클라우드 포인트의 중심 위치 계산
        center = np.mean(np.asarray(cloud.points), axis=0)
        # center list로 바꾸기
        center_list = center.tolist()
        list6ds = []
        for point in center_list:
            list6ds.append(point)
        
        aabb = cloud.get_axis_aligned_bounding_box()
        obb = cloud.get_oriented_bounding_box()
          # # OBB 박스의 방향 벡터 추출
        obb_rotation_matrix = obb.R
        obb_x_axis1 = obb_rotation_matrix[:, 0]  # X 축 방향 벡터
        obb_y_axis1 = obb_rotation_matrix[:, 1]  # Y 축 방향 벡터
        obb_z_axis1 = obb_rotation_matrix[:, 2]  # Z 축 방향 벡터

        # AABB 박스의 각 축 벡터
        aabb_x_axis1 = np.array([1.0, 0.0, 0.0])  # AABB X 축
        aabb_y_axis1 = np.array([0.0, 1.0, 0.0])  # AABB Y 축
        aabb_z_axis1 = np.array([0.0, 0.0, 1.0])  # AABB Z 축

        # 두 박스 간의 각도 계산
        angle_x = np.arccos(np.dot(obb_x_axis1, aabb_x_axis1))
        angle_y = np.arccos(np.dot(obb_y_axis1, aabb_y_axis1))
        angle_z = np.arccos(np.dot(obb_z_axis1, aabb_z_axis1))

        # 라디안을 도(degree)로 변환
        angle_x_deg = np.degrees(angle_x)
        angle_y_deg = np.degrees(angle_y)
        angle_z_deg = np.degrees(angle_z)

        list6ds.append(angle_z_deg)
        list6ds.append(angle_y_deg)
        list6ds.append(angle_x_deg)
        list6d.append(list6ds)
    # print(list6d)    
    return list6d    

def outputreal6d (xyz_output):
    list6d = []
    for cloud in xyz_output:
      obb = cloud.get_oriented_bounding_box()
      # obb.color = (0, 1, 0)
      
      center_list = obb.get_center().tolist()
      list6ds = []
      for point in center_list:
          list6ds.append(point)
      # 롤 (Roll), 피치 (Pitch), 요 (Yaw) 각도 계산
      roll_deg, pitch_deg, yaw_deg = calculate_rotation_angles(obb)
      # print("롤 (도):", roll_deg)
      # print("피치 (도):", pitch_deg)
      # print("요 (도):", yaw_deg)
      list6ds.append(roll_deg)
      list6ds.append(pitch_deg)
      list6ds.append(yaw_deg)
      list6d.append(list6ds)
    return list6d