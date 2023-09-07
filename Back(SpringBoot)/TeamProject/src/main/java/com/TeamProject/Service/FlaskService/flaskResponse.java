package com.TeamProject.Service.FlaskService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.threeOriginalPointCloud;
import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Dto.threePointCloudCoordinatesDTO;
import com.TeamProject.Dto.twoSegmentationCoordinatesDTO;
import com.TeamProject.Dto.twoSegmentationImageDTO;
import com.TeamProject.Repository.threeOriginalPointCloudRepository;
import com.TeamProject.Service.SpringBootService.threePointCloudCoordinatesService;
import com.TeamProject.Service.SpringBootService.twoSegmentationCoordinatesService;
import com.TeamProject.Service.SpringBootService.twoSegmentationImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class flaskResponse {

    
    // 2D_Segmentation
    private final twoSegmentationImageService twosegmentationimageservice;

    // 2D_coordinates
    private final twoSegmentationCoordinatesService twosegmentationcoordinatesservice;

    // 3D_PointCloud
    private final threeOriginalPointCloudRepository threeoriginalpointcloudrepository;

    // 3D_coordinates
    private final threePointCloudCoordinatesService threepointcloudcoordinatesservice;
    

    // 이미지 파일의 기본 URL
	private final String imageBaseURL = "http://10.125.121.183:8080/upload/image/";

    // Json 데이터 파싱
    public String parsing(String json, historyTable history) {
        // json을 array로 변환
        JSONArray jsonArray = new JSONArray(json);

        // 파싱할 json 데이터 객체로 저장.
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        // base64, 파일명 추출
        String imageValue = jsonObject.getString("image");
        String imageName = jsonObject.getString("image_name");
        //String pointCloudName = jsonObject.getString("ply_name");

        // 추출된 이미지 값 
        decoding(imageValue, imageName); // base64 Decoding
        twoSegmentationImage twosegmentationimage = SegDTO(imageName, history); // Segmentation 원본 저장
        threeOriginalPointCloud threeoriginalpointcloud = PointDTO(history);
        
        JSONArray detectionArray = jsonObject.getJSONArray("detections");

        for(int i = 0; i < detectionArray.length(); i++) {
            JSONObject detectionObject = detectionArray.getJSONObject(i);

            // 필요한 데이터 추출 및 처리
            //int objectNum = detectionObject.getInt("Object_num");
            double accuracy = detectionObject.getDouble("accuracy"); //정확도
          
            int className = (int) detectionObject.getDouble("class_name"); // 탐지객체 
            String classNameString = "";
            switch(className) {
                case 0 : classNameString = "bongji";
                    break;
                case 1 : classNameString = "box";
                    break;
                case 2 : classNameString = "canSnack";
                    break;
                case 3 : classNameString = "energdrink";
                    break;
                case 4 : classNameString = "milk";
                    break;
            }
       
            JSONArray xCoordinates = detectionObject.getJSONArray("x_coordinates");
            JSONArray yCoordinates = detectionObject.getJSONArray("y_coordinates");
            JSONArray xPoint = detectionObject.getJSONArray("x_point"); // 3pd
            JSONArray yPoint = detectionObject.getJSONArray("y_point"); // 3pd
            JSONArray zPoint = detectionObject.getJSONArray("z_point"); // 3pd
            pointCloudCenter(xPoint.toString(), yPoint.toString(), zPoint.toString());
            JSONArray boxInfo = detectionObject.getJSONArray("box_info"); // 제이슨에 배열 파싱

            double xBox = boxInfo.optDouble(0); // 배열값 저장
            double yBox = boxInfo.optDouble(1);
            double width = boxInfo.optDouble(2);
            double height = boxInfo.optDouble(3);
            twoCoorDTO(accuracy, classNameString, xCoordinates.toString(), yCoordinates.toString(), xBox, yBox, width, height, twosegmentationimage); // Segmentation수치 저장.
            threeCoorDTO(classNameString, xPoint.toString(), yPoint.toString(), zPoint.toString(), threeoriginalpointcloud); // PointCloud 수치 저장

        }
        return imageName;
    }

    // Base64 디코딩
    public void decoding(String imageValue, String imageName) {

        byte[] imageData = Base64.getDecoder().decode(imageValue);

        // 이미지 파일 저장 및 경로
        String imagePath = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/Segmentation/" + imageName;

        // 이미지 저장
        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
            fos.write(imageData);
            System.out.println("이미지 저장 완료: " + imagePath);
        } catch (IOException e) {
            System.out.println("이미지 저장 실패: " + e.getMessage());
        }
    }

    // 2D_Segmentation
    public twoSegmentationImage SegDTO(String imageName, historyTable history) {
        twoSegmentationImageDTO twosegmentationimagedto = new twoSegmentationImageDTO();

        // DTO 통해서 전달.
        twosegmentationimagedto.setTwoSegmentationPath(imageBaseURL + imageName);
        twosegmentationimagedto.setHistoryId(history);
        return twosegmentationimageservice.segmentationImage(twosegmentationimagedto);
    }

    // 2D_coordinates
    public void twoCoorDTO(double accuracy, String classNameString, String xCoordinates, String yCorrdinates, double xBox, double yBox, double width, double height, twoSegmentationImage twosegmentationimage) {
        twoSegmentationCoordinatesDTO twosegmentationcorrdinatesdto = new twoSegmentationCoordinatesDTO();

        // DTO 통해서 전달
        twosegmentationcorrdinatesdto.setTwoObjectAcc(accuracy);
        twosegmentationcorrdinatesdto.setTwoObjectId(classNameString);
        twosegmentationcorrdinatesdto.setXList(xCoordinates);
        twosegmentationcorrdinatesdto.setYList(yCorrdinates);
        twosegmentationcorrdinatesdto.setXBox(xBox);
        twosegmentationcorrdinatesdto.setYBox(yBox);
        twosegmentationcorrdinatesdto.setWidth(width);
        twosegmentationcorrdinatesdto.setHeight(height);
        twosegmentationcorrdinatesdto.setTwoSegmentationId(twosegmentationimage);
        twosegmentationcoordinatesservice.twoCoordinates(twosegmentationcorrdinatesdto);        
    }

    // 3D_PointCloud
    public threeOriginalPointCloud PointDTO(historyTable history) {
        return threeoriginalpointcloudrepository.findByHistoryId(history);
    }

    // 3D_coordinates
    public void threeCoorDTO(String classNameString, String xPoint, String yPoint, String zPoint, threeOriginalPointCloud threeoriginalpointcloud) {
        threePointCloudCoordinatesDTO threepointcloudcoordinatesdto = new threePointCloudCoordinatesDTO();

        // DTO 통해서 전달
        threepointcloudcoordinatesdto.setThreeObjectId(classNameString);
        threepointcloudcoordinatesdto.setXList(xPoint);
        threepointcloudcoordinatesdto.setYList(yPoint);
        threepointcloudcoordinatesdto.setZList(zPoint);
        threepointcloudcoordinatesdto.setThreeOriginalId(threeoriginalpointcloud);
        threepointcloudcoordinatesservice.threeCoordinates(threepointcloudcoordinatesdto);
    }

    //  3D 중점구하기
    public void pointCloudCenter(String xPoint, String yPoint, String zPoint) {
 
        Point(xPoint);
        Point(yPoint);
        Point(zPoint);

        // 좌표생성
        List<Point3D> coordinates = new ArrayList<>();
        for(int i = 0; i < Point(xPoint).size(); i++) {
            coordinates.add(new Point3D(Point(xPoint).get(i), Point(yPoint).get(i), Point(yPoint).get(i)));
        }

        // 좌표의 개수
        int numCoordinates = coordinates.size();

        double sumX = 0.0;
        double sumY = 0.0;
        double sumZ = 0.0;

        // 모든 좌표의 X, Y, Z 좌표 합산
        for (Point3D coord : coordinates) {
            sumX += coord.getX();
            sumY += coord.getY();
            sumZ += coord.getZ();
        }

        // X, Y, Z 좌표의 평균을 계산하여 중심점 찾기
        double centerX = sumX / numCoordinates;
        double centerY = sumY / numCoordinates;
        double centerZ = sumZ / numCoordinates;

        // 중심점 출력
        System.out.println("중심점 좌표: X=" + centerX + ", Y=" + centerY + ", Z=" + centerZ);
        rotation(centerX, centerY, centerZ);
    }

    // 3D 좌표 전처리
    public List<Double> Point(String point) {
        point = point.replaceAll("\\[|\\]", ""); // "[", "]" 를  "" 대체한다. // 정규표현식
        String[] List = point.split(","); // "," 기준으로 나누고 List에 저장
        ArrayList<Double> dble = new ArrayList<>(); // 객체 선언
        for(String p : List) {
            dble.add(Double.parseDouble(p.trim()));
        }
        return dble;
    }
    public void rotation(double centerX, double centerY, double centerZ) {
        // 객체의 초기 위치 좌표
        // double x = centerX;
        // double y = centerY;
        // double z = centerZ;

        // 초기 객체의 위치 좌표
        double initialX = 0.0;
        double initialY = 0.0;
        double initialZ = 0.0;

        // 초기 객체의 방향 벡터 (예: 초기 방향이 (1, 0, 0)인 경우)
        //Vector3D initialDirection = new Vector3D(1.0, 0.0, 0.0);

        // 포인트 클라우드의 중심 좌표를 사용하여 위치 변화 계산
        double deltaX = centerX - initialX;
        double deltaY = centerY - initialY;
        double deltaZ = centerZ - initialZ;

        // 위치 변화를 반영하여 새로운 객체의 위치 계산
        double newX = initialX + deltaX;
        double newY = initialY + deltaY;
        double newZ = initialZ + deltaZ;

        // 새로운 위치와 초기 방향을 사용하여 6D 포즈 계산
        Vector3D newDirection = new Vector3D(newX - initialX, newY - initialY, newZ - initialZ);

        // 결과 출력
        System.out.println("새로운 위치 (X, Y, Z): (" + newX + ", " + newY + ", " + newZ + ")");
        System.out.println("새로운 방향 (X, Y, Z): (" + newDirection.getX() + ", " + newDirection.getY() + ", " + newDirection.getZ() + ")");
    }

}

class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}



