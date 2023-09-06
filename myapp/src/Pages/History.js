import '../Styles/History.css'
import Stats from '../Components/Stats'
import Carousel from '../Components/Carousel'
import List from '../Components/List';
import { useEffect } from 'react';

const CAROUSEL_IMAGES = [
    'https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg',
    'https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg',
    'https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg',
  ]

export default function History() {

    
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
                console.log(data);
            })
            .catch((error) => console.error('데이터 불러오기 오류: ', error));
    }, []);

    return (
        <main>
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
                        <Carousel carouselList={CAROUSEL_IMAGES}/>                        
                    </div>
                    <div className='content_dateList'>
                        <List />
                    </div>
                </div>
            </div>
        </main>

    );
}