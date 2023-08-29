package com.TeamProject.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamProject.Domain.twoOriginalImage;
import com.TeamProject.Repository.imageUploadRepository;



@Service
public class imageUploadService {

    @Autowired
    imageUploadRepository imageuploadrepository;

    // 이미지 파일의 기본 URL
	private final String imageBaseURL = "http://10.125.121.183:8080/";

    public ResponseEntity<String> uploadService(MultipartFile imageFile) {

        twoOriginalImage twooriginalimage = new twoOriginalImage();

        if (imageFile != null && !imageFile.isEmpty()) {
		    try {
		        String imageFileName = saveImage(imageFile);
		        twooriginalimage.setTwoOriginalPath(imageBaseURL + imageFileName); // 이미지 파일 이름 설정
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		imageuploadrepository.save(twooriginalimage);
        return null;
    }

    // 이미지 저장 메서드
    private String saveImage(MultipartFile imageFile) throws IOException {
    	if (!imageFile.isEmpty()) {
            try {
                // 원본 파일 이름 가져오기
                String originalFilename = imageFile.getOriginalFilename();
                // 파일 저장 경로 설정 (필요에 따라 변경해주세요)
                String savePath = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/";

                // 저장할 파일 객체 생성
                File savedFile = new File(savePath + originalFilename);

                // MultipartFile의 내용을 파일에 저장
                imageFile.transferTo(savedFile);

				return originalFilename;
                //return savePath + originalFilename; // 저장된 파일 이름 반환
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 발생 시 null이나 다른 값을 반환하거나 처리하는 등의 방법 선택
            }
        }
        return null; // 파일이나 이미지가 없을 경우
    }
}
