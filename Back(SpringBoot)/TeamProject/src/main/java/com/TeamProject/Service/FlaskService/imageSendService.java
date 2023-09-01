package com.TeamProject.Service.FlaskService;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.TeamProject.Domain.historyTable;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class imageSendService {

    // base64 디코딩
    private final flaskResponse flaskresponse;
    
    // 로컬 이미지 파일을 Flask 서버로 전송하는 로직 구현
    public String sendImage(MultipartFile pngFile, MultipartFile plyFile, historyTable history) {

        // Flask 서버의 엔드포인트 URL
        String flaskServerUrl = "http://10.125.121.180:80/uploadFlask";

        // 이미지 파일의 이름 가져오기(타입 변환)
        String twoImageName = pngFile.getOriginalFilename();
        String threeImageName = plyFile.getOriginalFilename();

        // 전송하고자 하는 로컬 파일의 경로 (변경 가능)
        String localFilePath2D = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/2D/" + twoImageName;
        String localFilePath3D = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/3D/" + threeImageName;

        // HTTP POST 요청 생성
        RestTemplate restTemplate = new RestTemplate();

        // 파일 전송을 위한 MultiValueMap 설정
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        // 로컬 파일을 파일 리소스로 변환하여 첨부
        File localFile2d = new File(localFilePath2D);
        File localFile3d = new File(localFilePath3D);

        // 2d, 3d map 추가
        map.add("pngFile", new FileSystemResource(localFile2d));
        map.add("plyFile", new FileSystemResource(localFile3d));

        // HTTP Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // HTTP POST 요청 전송 및 응답 처리(응답 객체)
        ResponseEntity<String> response = restTemplate.exchange(flaskServerUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("로컬 이미지 전송 성공");

            // 받아온 응답 데이터 확인
            String responseBody = response.getBody();

            // 응답 데이터 파싱하기.
            return flaskresponse.parsing(responseBody, history);

        } else {
            return "로컬 이미지 전송 실패: " + response.getStatusCode();
            // 실패 시 예외 처리 또는 로그 등 추가 작업 수행
        }
    }
}
