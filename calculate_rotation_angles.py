import numpy as np

# 롤 (Roll), 피치 (Pitch), 요 (Yaw) 각도 계산 함수
def calculate_rotation_angles(obb):
    # 초기 회전 행렬 (단위 행렬) 생성
    initial_rotation_matrix = np.identity(3)

    # 현재 회전 행렬 가져오기
    current_rotation_matrix = obb.R
    
    # 초기 방향과 현재 방향 사이의 차이 계산
    rotation_difference_matrix = current_rotation_matrix @ np.linalg.inv(initial_rotation_matrix)
    
    # 차이 행렬을 Euler 각도로 변환
    roll_rad = np.arctan2(rotation_difference_matrix[2, 1], rotation_difference_matrix[2, 2])
    pitch_rad = np.arctan2(-rotation_difference_matrix[2, 0], np.sqrt(rotation_difference_matrix[2, 1]**2 + rotation_difference_matrix[2, 2]**2))
    yaw_rad = np.arctan2(rotation_difference_matrix[1, 0], rotation_difference_matrix[0, 0])
    
    # 라디안 각도를 도(degree)로 변환
    roll_deg = np.degrees(roll_rad)
    pitch_deg = np.degrees(pitch_rad)
    yaw_deg = np.degrees(yaw_rad)

    return roll_deg, pitch_deg, yaw_deg