package com.TeamProject.Service.SpringBootService;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.threeOriginalPointCloud;
import com.TeamProject.Domain.twoOriginalImage;
import com.TeamProject.Repository.imageUploadRepository;
import com.TeamProject.Repository.threeOriginalPointCloudRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //초기화 되지 않은 모든 final 필드 & @NonNull 마크가 있는 필드를 초기화하는 생성자 생성
@Service
public class imageUploadService {

    private final imageUploadRepository imageuploadrepository; // 2D 원본 이미지 
    private final threeOriginalPointCloudRepository threeoriginalpointcloudrepository; // 3D 포인트클라우드

    // 이미지 파일의 기본 URL
	private final String imageBaseURL = "http://10.125.121.183:8080/upload/image/"; // 클라이언트 실행
    //private final String imageBaseURL = "http://localhost:8080/upload/image/"; // 로컬 실행

    @Transactional
    public ResponseEntity<String> uploadService(MultipartFile pngFile, MultipartFile plyFile, historyTable history) {

        twoOriginalImage twooriginalimage = new twoOriginalImage();
        threeOriginalPointCloud threeoriginalpointcloud = new threeOriginalPointCloud();

        // 2d, 3d
        if (pngFile != null && !pngFile.isEmpty()) {
		    try {
		        String[] imageFileName = saveImage(pngFile, plyFile);
		        twooriginalimage.setTwoOriginalPath(imageBaseURL + imageFileName[0]); // 2d 이미지 파일 이름 설정
                twooriginalimage.setHistoryId(history);

                threeoriginalpointcloud.setThreeOriginalPath(imageBaseURL + imageFileName[1]);// 3d 이미지 파일 이름 설정
                threeoriginalpointcloud.setHistoryId(history);
            } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		imageuploadrepository.save(twooriginalimage);
        threeoriginalpointcloudrepository.save(threeoriginalpointcloud);
        return null;
    }

    // 이미지 저장 메서드
    private String[] saveImage(MultipartFile pngFile, MultipartFile plyFile) throws IOException {
    	if (!pngFile.isEmpty() && !plyFile.isEmpty()) {
            try {
                // 원본 파일 이름 가져오기
                String original2D = pngFile.getOriginalFilename();
                String original3D = plyFile.getOriginalFilename();

                // 파일 저장 경로 설정 (필요에 따라 변경해주세요)
                String savePath2D = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/2D/";
                String savePath3D = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/3D/";

                // 저장할 파일 객체 생성
                File savedFile2D = new File(savePath2D + original2D);
                File savedFile3D = new File(savePath3D + original3D);

                // MultipartFile의 내용을 파일에 저장
                pngFile.transferTo(savedFile2D);
                plyFile.transferTo(savedFile3D);

                String[] originalFile = new String[] {original2D, original3D};
        
				return originalFile;
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 발생 시 null이나 다른 값을 반환하거나 처리하는 등의 방법 선택
            }
        }
        return null; // 파일이나 이미지가 없을 경우
    }
}
