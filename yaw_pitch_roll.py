import numpy as np
import math

def yaw_pitch_roll_to_rotation_matrix(yaw, pitch, roll):
    # 각도를 라디안으로 변환
    yaw_rad = math.radians(yaw)
    pitch_rad = math.radians(pitch)
    roll_rad = math.radians(roll)

    # Yaw, Pitch, Roll 회전 행렬 계산
    yaw_matrix = np.array([
        [math.cos(yaw_rad), -math.sin(yaw_rad), 0],
        [math.sin(yaw_rad), math.cos(yaw_rad), 0],
        [0, 0, 1]
    ])

    pitch_matrix = np.array([
        [math.cos(pitch_rad), 0, math.sin(pitch_rad)],
        [0, 1, 0],
        [-math.sin(pitch_rad), 0, math.cos(pitch_rad)]
    ])

    roll_matrix = np.array([
        [1, 0, 0],
        [0, math.cos(roll_rad), -math.sin(roll_rad)],
        [0, math.sin(roll_rad), math.cos(roll_rad)]
    ])

    # 회전 행렬을 순서대로 곱해서 최종 회전 행렬 생성
    rotation_matrix = np.dot(np.dot(roll_matrix, pitch_matrix), yaw_matrix)
    return rotation_matrix

# 예시 각도 (단위: 도)
# yaw_angle = 30
# pitch_angle = 45
# roll_angle = 60
yaw_angle = 180
pitch_angle = 0
roll_angle = 0

# 회전 행렬 생성
rotation_matrix = yaw_pitch_roll_to_rotation_matrix(yaw_angle, pitch_angle, roll_angle)