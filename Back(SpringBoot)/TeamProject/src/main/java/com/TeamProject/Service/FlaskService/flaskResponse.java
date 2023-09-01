package com.TeamProject.Service.FlaskService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Dto.twoSegmentationCoordinatesDTO;
import com.TeamProject.Dto.twoSegmentationImageDTO;
import com.TeamProject.Repository.twoSegmentationRepository;
import com.TeamProject.Service.SpringBootService.twoSegmentationCoordinatesService;
import com.TeamProject.Service.SpringBootService.twoSegmentationImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class flaskResponse {

    
    // 2D_Segmentation
    private final twoSegmentationImageService twosegmentationimageservice;

    // 2D_Segmentation_Repository
    private final twoSegmentationRepository twosegmentationrepository;

    // 2D_coordinates
    private final twoSegmentationCoordinatesService twosegmentationcoordinatesservice;

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

        // 추출된 이미지 값 
        decoding(imageValue, imageName); // base64 Decoding
        twoSegmentationImage twosegmentationimage = SegDTO(imageName, history); // Segmentation 원본 저장

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
            JSONArray boxInfo = detectionObject.getJSONArray("box_info"); // 제이슨에 배열 파싱

            double xBox = boxInfo.optDouble(0); // 배열값 저장
            double yBox = boxInfo.optDouble(1);
            double width = boxInfo.optDouble(2);
            double height = boxInfo.optDouble(3);
            twoCoorDTO(accuracy, classNameString, xCoordinates.toString(), yCoordinates.toString(), xBox, yBox, width, height, twosegmentationimage); // Segmentation수치 저장.
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
}
