import { useEffect, useRef, useState } from 'react';
import { useLocation } from 'react-router-dom';
import '../Styles/Service.css'
import NavBar from '../Components/NavBar';

export default function Service() {

    const location = useLocation();
    const uploadedImageUrl = location.state?.uploadedImageUrl;
    const uploadedPoseResults = location.state?.uploadedPoseResults;
    const uploadedBoxInfo = location.state?.uploadedBoxInfo;

    const objectId = uploadedPoseResults ? uploadedPoseResults.map(item => item.objectId) : [];

    const [selectedIndex, setSelectedIndex] = useState(null);

    const [imageSize, setImageSize] = useState({ width: 0, height: 0 });
    const imageRef = useRef(null);

    const handleDateClick = (index) => {
        setSelectedIndex(index === selectedIndex ? null : index);
        console.log(index);
        // 해당 인덱스에 대한 박스 정보 업데이트
    }

    useEffect(() => {
        if (uploadedImageUrl) {
            console.log("Uploaded Image URL: ", uploadedImageUrl);
            console.log("Uploaded pose Results: ", uploadedPoseResults);
            console.log("Uploaded Box Info: ", uploadedBoxInfo);
            console.log("boxInfo: ", uploadedBoxInfo[0].xbox);

            // 이미지 로딩이 완료된 후 이미지 크기를 측정
            if (imageRef.current.complete) {
                setImageSize({
                    width: imageRef.current.width,
                    height: imageRef.current.height,
                });
            } else {
                // 이미지 로딩 완료 이벤트 핸들러 설정
                imageRef.current.onload = () => {
                    setImageSize({
                        width: imageRef.current.width,
                        height: imageRef.current.height,
                    });
                };
            }
        }
    }, [uploadedImageUrl, uploadedPoseResults, uploadedBoxInfo]);

    // 선택한 박스 정보 가져오기
    const selectedBox = uploadedBoxInfo && selectedIndex !== null ? uploadedBoxInfo[selectedIndex] : null;

    return (
        <main>
            <NavBar />
            <div className='boxes'>
                <div className='imageBox'>
                    <img src={uploadedImageUrl ? `${uploadedImageUrl}` : ''} alt='' ref={imageRef} /> {/* 이미지 참조 설정 */}
                    {selectedBox && selectedIndex !== null && (
                        // 여기에서 빨간 사각형을 표시하고 애니메이션을 추가합니다.                        
                        <div
                            className='selectedBox'
                            style={{
                                position: 'absolute',
                                left: (selectedBox.xbox / imageRef.current.naturalWidth) * imageSize.width - 55,
                                top: (selectedBox.ybox / imageRef.current.naturalHeight) * imageSize.height + 150,
                                width: (selectedBox.width / imageRef.current.naturalWidth) * imageSize.width,
                                height: (selectedBox.height / imageRef.current.naturalHeight) * imageSize.height,
                            }}
                        ></div>
                    )}
                </div>
                <div className='informationBox'>
                    <div className='information_coordinates'>
                        {selectedIndex !== null ? (
                            <div className='coordinates_left'>
                                <p>x: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].x : " "}</p>
                                <p>y: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].y : " "}</p>
                                <p>z: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].z : " "}</p>
                            </div>
                        ) : '하기의 리스트를 클릭해주세요우'}
                        {selectedIndex !== null ? (
                            <div className='coordinates_right'>
                                <p>rx: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].rx : " "}</p>
                                <p>ry: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].ry : " "}</p>
                                <p>rz: {uploadedPoseResults ? uploadedPoseResults[selectedIndex].rz : " "}</p>
                            </div>
                        ) : null}
                    </div>
                    <div className='information_lists'>
                        <ul className='divide-y divide-gray-100 pr-8 overflow-y-scroll h-30' style={{ height: '500px' }}>
                            {objectId.map((item, index) => (
                                <li
                                    key={index}
                                    className={`flex justify-between relative gap-x-6 py-1 px-9 ${selectedIndex === index ? 'bg-gray-100 rounded-full' : ''}`}
                                    onClick={() => handleDateClick(index)}
                                    style={{ cursor: 'pointer', height: '70px' }}
                                >
                                    <div className='flex min-w-0 gap-x-4'>
                                        <div className='min-w-0 flex-auto'>
                                            <p className='text-5xl font-semibold leading-6 text-gray-900'>
                                                {item}{index}
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </main>
    );
}