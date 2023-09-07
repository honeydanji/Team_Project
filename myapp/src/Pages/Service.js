import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Breadcrumb from '../Components/Breadcrumb';
import '../Styles/Service.css'
import Nav from '../Components/Nav';

export default function Service() {

    const locaiton = useLocation();
    const uploadedImageUrl = locaiton.state?.uploadedImageUrl;

    useEffect(() => {
        if(uploadedImageUrl) {
            console.log("Uploaded Image URL: ", uploadedImageUrl);
            // 업로드된 이미지 URL을 사용하여 원하는 작업 수행
        }
    }, [uploadedImageUrl]);

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
                    <div className='information_coordinates'></div>
                    <div className='information_lists'></div>
                </div>
            </div>
        </main>
    );
}