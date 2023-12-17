package com.TeamProject.Controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TeamProject.Controller.Interface.ImageUploadInterface;
import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Service.FlaskService.ImageSendService;
import com.TeamProject.Service.SpringBootService.HistoryTableService;
import com.TeamProject.Service.SpringBootService.ImageUploadService;
import com.TeamProject.Service.SpringBootService.PoseDataService;
import com.TeamProject.Service.SpringBootService.TwoSegmentationCoordinatesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageUploadController implements ImageUploadInterface{
    

    private final ImageUploadService imageuploadservice; // SpringBoot 

    private final ImageSendService imagesendservice; // 외부 API 

    private final HistoryTableService historytableservice; // historyTable

    private final PoseDataService posedataservice; // poseData

    private final TwoSegmentationCoordinatesService twosegmentationcoordinatesservice; // segData
    
    @Override
    @PostMapping("/uploadSpring")
    public ResponseEntity<Object> uploadController(@RequestParam(name = "pngFile", required = false) MultipartFile pngFile,
                                                   @RequestParam(name = "plyFile", required = false) MultipartFile plyFile,
                                                   Authentication authentication) {
                                                    
        if (authentication == null) {
            return ResponseEntity.ok("회원이 아닙니다");
        } else {
            Map<String, Object> response = new HashMap<>();
            String pngFileCount = pngFile.getOriginalFilename();
            String plyFileCount = plyFile.getOriginalFilename();

            int dotCountPng = 0; // dot 갯수
            int dotCountPly = 0;

            // 파일 이름 제한
            for(int i = 0; i < pngFileCount.length(); i++) {
                if(pngFileCount.charAt(i) == '.') {
                    dotCountPng++;
                }
            }
            
            for(int i = 0; i < plyFileCount.length(); i++) {
                if(plyFileCount.charAt(i) == '.'){
                    dotCountPly++;
                }
            }
        
            if(dotCountPng >= 2 || dotCountPly >= 2) {
                return ResponseEntity.ok(response.put("Error", "파일명을 수정해주세요."));
                //return ResponseEntity.ok("파일명을 수정해주세요.");
            }
        
            // history Service
            HistoryTable history = historytableservice.historyUpdate(authentication);



            imageuploadservice.uploadService(pngFile, plyFile, history); // StringBoot 
            String name = imagesendservice.sendImage(pngFile, plyFile, history); // Flask
            response.put("url", "http://10.125.121.183:8080/upload/image/" + name);
            response.put("pose", posedataservice.poseDataDispaly(history));
            response.put("boxInfo", twosegmentationcoordinatesservice.boxInfo(history));
            return ResponseEntity.ok(response);
        }
    }   

    // 이미지 파일이 저장된 디렉토리 경로를 설정.
	private final String twoDImageDirectory = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/2D"; // 2d 원본이미지
    private final String segmentationImageDirectory = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/Segmentation"; // segmentation 이미지
    private final String threeDImageDirectory = "C:/Team_Project/Back(SpringBoot)/TeamProject/image/3D"; // 3d 포인트클라우드

	// 이미지 조회
    @Override
	@GetMapping("/upload/image/{imageName:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws MalformedURLException {

        // 이미지 확장자에 따라 저장 경로 선택
        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
        System.out.println("이미지 확장자 : " + imageExtension);
        String imageDirectory;

        // 확장자에 따른 경로 설정
        if("png".equalsIgnoreCase(imageExtension)) {
            imageDirectory = twoDImageDirectory;
        } else if("jpg".equalsIgnoreCase(imageExtension)) {
            imageDirectory = segmentationImageDirectory;
        } else if ("ply".equalsIgnoreCase(imageExtension)) {
            imageDirectory = threeDImageDirectory;
        } else {
            return null;
        }
        
        // 요청된 이미지 파일 이름을 사용하여 이미지 파일의 경로를 가져오기.
        Path imagePath = Paths.get(imageDirectory).resolve(imageName);
        Resource imageResource = new UrlResource(imagePath.toUri());

        // 이미지 파일이 존재하지 않는 경우.
        if (!imageResource.exists()) {
            // 이미지가 없을 경우에 대한 처리를 여기에 작성.
            return ResponseEntity.notFound().build();
        }

        // 이미지 파일을 응답으로 반환합니다.
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageResource);
    }  
}
