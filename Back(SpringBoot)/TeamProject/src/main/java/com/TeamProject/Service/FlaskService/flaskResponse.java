package com.TeamProject.Service.FlaskService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Repository.twoSegmentationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class flaskResponse {
    
    private final twoSegmentationRepository twosegmentationrepository;

    // 이미지 파일의 기본 URL
	private final String imageBaseURL = "http://10.125.121.183:8080/upload/image/"; // 클라이언트 실행

    // Json 데이터 파싱
    public void parsing(String json, historyTable history) {
        JSONArray jsonArray = new JSONArray(json);

        // "image" value 추출 후 변수 저장
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String imageValue = jsonObject.getString("image");
        String imageName = jsonObject.getString("image_name");

        // 추출된 이미지 값 
        decoding(imageValue, imageName, history);
    }
    
    // Base64 디코딩
    public void decoding(String imageValue, String imageName, historyTable history) {

        twoSegmentationImage twosegmentationimage = new twoSegmentationImage();

        byte[] imageData = Base64.getDecoder().decode(imageValue);

        // 이미지 파일 저장 및 경로
        String imagePath = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/Segmentation/" + imageName;

        // DB 파일 url 저장
        twosegmentationimage.setTwoSegmentationPath(imageBaseURL + imageName);
        twosegmentationimage.setHistoryId(history);
        twosegmentationrepository.save(twosegmentationimage);

        // 이미지 저장
        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
            fos.write(imageData);
            System.out.println("이미지 저장 완료: " + imagePath);
        } catch (IOException e) {
            System.out.println("이미지 저장 실패: " + e.getMessage());
        }
    }
}
