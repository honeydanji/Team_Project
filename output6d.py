import open3d as o3d
import numpy as np

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
        # print(center)
        # PCA를 수행하여 주요 주성분 벡터를 찾습니다.
        points = np.asarray(cloud.points)
        covariance_matrix = np.cov(points, rowvar=False)
        eigenvalues, eigenvectors = np.linalg.eigh(covariance_matrix)

        # 주요 주성분 벡터와 각각의 축과의 각도를 계산합니다.
        x_axis = np.array([1, 0, 0])  # x 축
        y_axis = np.array([0, 1, 0])  # y 축
        z_axis = np.array([0, 0, 1])  # z 축

        angle_x_rad = np.arccos(np.dot(eigenvectors[:, -1], x_axis) / (np.linalg.norm(eigenvectors[:, -1]) * np.linalg.norm(x_axis)))
        angle_x_deg = np.degrees(angle_x_rad)

        angle_y_rad = np.arccos(np.dot(eigenvectors[:, -1], y_axis) / (np.linalg.norm(eigenvectors[:, -1]) * np.linalg.norm(y_axis)))
        angle_y_deg = np.degrees(angle_y_rad)

        angle_z_rad = np.arccos(np.dot(eigenvectors[:, -1], z_axis) / (np.linalg.norm(eigenvectors[:, -1]) * np.linalg.norm(z_axis)))
        angle_z_deg = np.degrees(angle_z_rad)

        # print(f"요피치 롤(x 축 기준): {angle_x_deg}도")
        # print(f"요피치 롤(y 축 기준): {angle_y_deg}도")
        # print(f"요피치 롤(z 축 기준): {angle_z_deg}도")
        list6ds.append(angle_x_deg)
        list6ds.append(angle_y_deg)
        list6ds.append(angle_z_deg)
        list6d.append(list6ds)
    # print(list6d)    
    return list6d    
        