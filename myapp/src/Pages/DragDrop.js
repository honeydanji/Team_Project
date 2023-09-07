import { useCallback, useEffect, useRef, useState } from "react";
import '../Styles/DragDrop.css'
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Nav from "../Components/Nav";

export default function DragDrop() {
    const navigate = useNavigate();
    const [uploadedImageUrl, setUploadedImageUrl] = useState(null);

    const token = localStorage.getItem('token');
    console.log("token", token);

    // const isLoggedIn = useRecoilValue(isLoggedInState);
    // console.log("isLoggedIn:", isLoggedIn);

    // 이미지 업로드 및 URL 가져오기
    const handleImageUploadClick = () => {
        if (imageFile.length === 0) {
            alert('이미지를 첨부하세요.');
            const fileUploadInput = document.getElementById("fileUpload");
            fileUploadInput.value = ""; // 파일 선택 창 초기화
            fileUploadInput.click(); // 파일 선택 창 열기
            return; // 이미지가 첨부되지 않았다면 서버 요청을 보내지 않고 함수를 종료합니다.
        }

        console.log("imageFile: ", imageFile);

        const formData = new FormData();

        // 이미지 확장자에 따라 분리하여 저장
        for (const fileObj of imageFile) {
            const file = fileObj.object;
            const fileExtension = file.name.split(".").pop().toLowerCase(); // 파일 확장자 추출, pop(): 배열의 마지막 요소를 제거하고 반환

            if (fileExtension === 'png') {
                formData.append("pngFile", file);
            } else if (fileExtension === 'ply') {
                formData.append("plyFile", file);
            } else {
                alert(`지원하지 않는 확장자입니다: ${file.name}`);
                return; // 지원하지 않는 확장자의 파일이 포함되어 있으면 서버 요청을 보내지 않고 함수를 종료
            }

            console.log("file: ", file);
        }

        console.log("formData: ");
        for (var pair of formData.entries()) {
            console.log(pair[0] + ", " + pair[1]);
        }

        axios.post('http://10.125.121.183:8080/uploadSpring', formData, {
            headers: {
                'authorization': `${token}`,
                'Content-Type': 'multipart/form-data',
            }
        })
            .then((res) => {
                if (res.status === 200) {
                    const imageUrl = res.data.url; // 이미지 URL을 서버 응답에서 추출
                    setUploadedImageUrl(imageUrl); // 상태로 이미지 URL 저장              
                } else {
                    console.error('Image upload failed.')
                }
            })
            .catch((err) => {
                console.error('Error uploading image:', err);
                alert("오류 발생")
            });
    };

    useEffect(() => {
        if (uploadedImageUrl !== null) {
            console.log("uploadedImageUrl: ", uploadedImageUrl); // 이미지 URL이 업데이트될 때마다 로그 출력
            alert("이미지 전송 완료");
            navigate("/service", { state: { uploadedImageUrl } }); //Service 페이지로 이동
        }
    }, [uploadedImageUrl, navigate]);


    const [isDragging, setIsDragging] = useState(false);
    const [imageFile, setImageFile] = useState([]);
    const dragRef = useRef(null);
    const fileId = useRef(0);

    const onChangeFiles = useCallback((e) => {
        let selectFiles = [];
        let tempFiles = imageFile;

        if (e.type === "drop") {
            selectFiles = e.dataTransfer.files;
        } else {
            selectFiles = e.target.files;
        }

        for (const file of selectFiles) {
            tempFiles = [...tempFiles, { id: fileId.current++, object: file }];
        }

        setImageFile(tempFiles);
    }, [imageFile]);

    const handleFilterFile = useCallback((id) => {
        setImageFile(imageFile.filter((file) => file.id !== id));
    }, [imageFile]);

    const handleDragIn = useCallback((e) => {
        e.preventDefault();
        e.stopPropagation();
    }, []);

    const handleDragOut = useCallback((e) => {
        e.preventDefault();
        e.stopPropagation();
        setIsDragging(false);
    }, []);

    const handleDragOver = useCallback((e) => {
        e.preventDefault();
        e.stopPropagation();

        if (e.dataTransfer.files) {
            setIsDragging(true);
        }
    }, []);

    const handleDrop = useCallback((e) => {
        e.preventDefault();
        e.stopPropagation();

        onChangeFiles(e);
        setIsDragging(false);
    }, [onChangeFiles]);

    const initDragEvents = useCallback(() => {
        if (dragRef.current !== null) {
            dragRef.current.addEventListener("dragenter", handleDragIn);
            dragRef.current.addEventListener("dragleave", handleDragOut);
            dragRef.current.addEventListener("dragover", handleDragOver);
            dragRef.current.addEventListener("drop", handleDrop);
        }
    }, [handleDragIn, handleDragOut, handleDragOver, handleDrop]);

    const resetDragEvents = useCallback(() => {
        if (dragRef.current !== null) {
            dragRef.current.removeEventListener("dragenter", handleDragIn);
            dragRef.current.removeEventListener("dragleave", handleDragOut);
            dragRef.current.removeEventListener("dragover", handleDragOver);
            dragRef.current.removeEventListener("drop", handleDrop);
        }
    }, [handleDragIn, handleDragOut, handleDragOver, handleDrop]);

    useEffect(() => {
        initDragEvents();

        return () => resetDragEvents();
    }, [initDragEvents, resetDragEvents])

    return (
        <div>
            {/* <Nav/> */}
            <div className="DragDrop">
                <input type="file" id="fileUpload" style={{ display: "none" }} multiple={true} onChange={onChangeFiles} />
                <label className={isDragging ? "DrageDrop-File-Dragging" : "DragDrop-File"} htmlFor="fileUpload" ref={dragRef}>
                    <div>Drag and Drop<br />the images here</div>
                </label>

                <div className="DragDrop-Files">
                    {imageFile.length > 0 && imageFile.map((file) => {
                        const { id, object } = file;

                        return (
                            <div key={id}>
                                <div>{object.name}</div>
                                <div className="DragDrop-Files-Filter" onClick={() => handleFilterFile(id)}>X</div>
                            </div>
                        );
                    })}
                </div>
                <div className="Upload-Button">
                    <button onClick={handleImageUploadClick}>Upload</button>
                </div>
            </div>
        </div>
    )
}