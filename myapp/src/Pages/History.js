import '../Styles/History.css'
import Carousel from '../Components/Carousel'
import List from '../Components/List';
import { useEffect, useState } from 'react';
import NavBar from '../Components/NavBar';
import jwtDecode from "jwt-decode";

export default function History() {

    // const navBgColour = 'bg-white'; // History 페이지에서의 Nav 색상 설정

    const [dateData, setDateData] = useState([]); // 서버에서 받은 데이터를 저장할 상태 변수
    const [imagesData, setImagesData] = useState([]); // 서버에서 받은 데이터에서 이미지를 저장

    const token = localStorage.getItem('token');
    // 토큰 추출 및 이름 저장
    // token decoding
    const splitToken = token.split(" ")[1];
    const decodedToken = jwtDecode(splitToken);
    const extractedName = decodedToken.name;
    console.log("extractedName: ", extractedName);


    useEffect(() => {
        const token = localStorage.getItem('token');
        console.log("token: ", token);

        fetch('http://10.125.121.183:8080/history', {
            method: 'GET',
            headers: {
                'authorization': `${token}`,
                'Content-Type': 'application/json',
            },
        })
            .then((res) => res.json())
            .then((data) => {
                console.log("raw Data: ", data);

                const keys = Object.keys(data);
                setDateData(keys); // 서버에서 받은 데이터를 상태 변수에 저장

                const twoSegmentationPaths = Object.values(data)
                    .map(item => item)
                    .filter(path => path); // 이미지가 null or undefined인 경우 제거                
                setImagesData(twoSegmentationPaths);

                console.log("twoSegmentation: ", twoSegmentationPaths);
            })
            .catch((error) => console.error('데이터 불러오기 오류: ', error));
    }, []);

    return (
        <main>
            <div className="frame">
                <NavBar />
                <div className="header">
                    <div className='header_title'>
                        <p className='header_mainTitle'> Your History </p>
                        <p className='header_subTitle'>
                            <span>Welcome!</span>
                            <span style={{ textDecoration: 'underline', textDecorationThickness: '1.5px' }}>{extractedName}</span>
                        </p>
                    </div>
                </div>
                <div className='content'>
                    <div className='content_carousel'>
                        <Carousel carouselList={imagesData} />
                    </div>
                    <div className='content_dateList'>
                        {/* dateData를 List 컴포넌트에 전달 */}
                        <List dateData={dateData} />
                    </div>
                </div>
            </div>
        </main>
    );
}