import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import Breadcrumb from '../Components/Breadcrumb';
import '../Styles/Service.css'
import Nav from '../Components/Nav';

const test = [
    {
        date: '2023-09-01',
    },
    {
        date: '2023-09-02',
    },
    {
        date: '2023-09-03',
    },
    {
        date: '2023-09-04',
    },
    {
        date: '2023-09-05',
    },
    {
        date: '2023-09-06',
    },
    {
        date: '2023-09-07',
    },
    {
        date: '2023-09-08',
    },
    {
        date: '2023-09-09',
    },
    {
        date: '2023-09-10',
    },
    {
        date: '2023-09-11',
    },
    {
        date: '2023-09-12',
    },
    {
        date: '2023-09-13',
    },
    {
        date: '2023-09-14',
    },
    {
        date: '2023-09-15',
    },
]

export default function Service() {

    const locaiton = useLocation();
    const uploadedImageUrl = locaiton.state?.uploadedImageUrl;
    const uploadedPoseResults = locaiton.state?.uploadedPoseResults;

    const objectId = uploadedPoseResults.map(item => item.objectId);

    const [selectedIndex, setSelectedIndex] = useState(null);

    const handleDateClick = (index) => {
        setSelectedIndex(index === selectedIndex ? null : index);
        console.log(index);
    }

    useEffect(() => {
        if (uploadedImageUrl) {
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