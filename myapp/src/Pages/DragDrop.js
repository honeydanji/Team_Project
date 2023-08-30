import { useCallback, useEffect, useRef, useState } from "react";
import '../Styles/DragDrop.css'
import axios from "axios";

export default function DragDrop() { 

    const handleImageUploadClick = () => {
        const formData = new FormData();

        for(const file of imageFile.map(fileObj => fileObj.object)) {
            formData.append("pngFile", file);
            formData.append("plyFile", file);
            console.log(file);
        }

        console.log(formData);
        console.log(imageFile);

        axios.post('http://10.125.121.183:8080/uploadSpring', formData, {
            headers: {
                'Content-Type' : 'multipart/form-data'
            }
        })
        .then((res) => {
            alert("이미지 전송 완")
        })
        .catch((err) => {
            alert("오류 발생")
        });
    };

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
            tempFiles = [...tempFiles, {id: fileId.current++, object: file}];
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

        if(e.dataTransfer.files) {
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
        if(dragRef.current !== null) {
            dragRef.current.addEventListener("dragenter", handleDragIn);
            dragRef.current.addEventListener("dragleave", handleDragOut);
            dragRef.current.addEventListener("dragover", handleDragOver);
            dragRef.current.addEventListener("drop", handleDrop);
        }
    }, [handleDragIn, handleDragOut, handleDragOver, handleDrop]);

    const resetDragEvents = useCallback(() => {
        if(dragRef.current !== null) {
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
        <div className="DragDrop">
            <input type="file" id="fileUpload" style={{ display: "none"}} multiple={true} onChange={onChangeFiles} />
            <label className={isDragging ? "DrageDrop-File-Dragging" : "DragDrop-File"} htmlFor="fileUpload" ref={dragRef}>
                <div>Drag and Drop<br/>the images here</div>
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
    )
}