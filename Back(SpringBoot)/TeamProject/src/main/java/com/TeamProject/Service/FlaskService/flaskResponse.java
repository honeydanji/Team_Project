package com.TeamProject.Service.FlaskService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

public class flaskResponse {

    // Json 데이터 파싱
    public void parsing(String json) {
        JSONArray jsonArray = new JSONArray(json);

        // "image" value 추출 후 변수 저장
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String imageValue = jsonObject.getString("image");
        String imageName = jsonObject.getString("image_name");

        // 추출된 이미지 값 
        //System.out.println(imageValue);
        decoding(imageValue, imageName);
    }
    
    // Base64 디코딩
    public void decoding(String imageValue, String image_name) {
        
        byte[] imageData = Base64.getDecoder().decode(imageValue);

        // 이미지 파일 저장 및 경로
        String imagePath = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/Segmentation/" + image_name;
    
        // 이미지 저장
        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
            fos.write(imageData);
            System.out.println("이미지 저장 완료: " + imagePath);
        } catch (IOException e) {
            System.out.println("이미지 저장 실패: " + e.getMessage());
        }
    }
}
