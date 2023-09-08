import '../Styles/History.css'
import Stats from '../Components/Stats'
import Carousel from '../Components/Carousel'
import List from '../Components/List';
import { useEffect, useState } from 'react';
import Nav from '../Components/Nav';

const CAROUSEL_IMAGES = [
    'https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg',
    'https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg',
    'https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg',
]

export default function History() {

    // const navBgColour = 'bg-white'; // History 페이지에서의 Nav 색상 설정

    const [dateData, setDateData] = useState([]); // 서버에서 받은 데이터를 저장할 상태 변수

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
                setDateData(data); // 서버에서 받은 데이터를 상태 변수에 저장
            })
            .catch((error) => console.error('데이터 불러오기 오류: ', error));
    }, []);

    return (
        <main>
            <Nav />
            <div className="frame">
                <div className="header">
                    <img className="header_logo" src="/images/darelogo.jpg" alt="" />
                    <div className='header_title'>
                        <p className='header_mainTitle'> Your History </p>
                        <p className='header_subTitle'>
                            <span>Welcome!</span>
                            <span style={{ textDecoration: 'underline', textDecorationThickness: '1.5px' }}>Joe Wonjun</span>
                        </p>
                    </div>
                </div>
                <div className='header_stats'>
                    <Stats />
                </div>
                <div className='content'>
                    <div className='content_carousel'>
                        <Carousel carouselList={CAROUSEL_IMAGES} />
                    </div>
                    <div className='content_dateList'>
                        <List dateData={dateData} /> {/* dateData를 List 컴포넌트에 전달 */}
                    </div>
                </div>
            </div>
        </main>
    );
}