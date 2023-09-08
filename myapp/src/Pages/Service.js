import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Breadcrumb from '../Components/Breadcrumb';
import '../Styles/Service.css'
import Nav from '../Components/Nav';

export default function Service() {

    const locaiton = useLocation();
    const uploadedImageUrl = locaiton.state?.uploadedImageUrl;
    const uploadedPoseResults = locaiton.state?.uploadedPoseResults;

    useEffect(() => {
        if(uploadedImageUrl) {
            console.log("Uploaded Image URL: ", uploadedImageUrl);
            console.log("Uploaded pose Results: ", uploadedPoseResults);
            // 업로드된 이미지 URL을 사용하여 원하는 작업 수행
        }
    }, [uploadedImageUrl, uploadedPoseResults]);

    return (
        <main>
            <Nav />
            <div className='breadcrumbBox'>
                <Breadcrumb />
            </div>
            <div className='boxes'>
                <div className='imageBox'>
                    <img src={uploadedImageUrl ? `${uploadedImageUrl}` : ''} alt='' />
                </div>
                <div className='informationBox'>
                    <div className='information_coordinates'>
                        <p>x: {uploadedPoseResults[0].x}</p>
                        <p>y: {uploadedPoseResults[0].y}</p>
                        <p>z: {uploadedPoseResults[0].z}</p>
                        <p>rx: {uploadedPoseResults[0].rx}</p>
                        <p>ry: {uploadedPoseResults[0].ry}</p>
                        <p>rz: {uploadedPoseResults[0].rz}</p>
                    </div>
                    <div className='information_lists'></div>
                </div>
            </div>
        </main>
    );
}