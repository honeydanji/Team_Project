import { useEffect } from 'react';
import Breadcrumb from '../Components/Breadcrumb';
import '../Styles/Service.css'

export default function Service() {

    useEffect(() => {
        fetch('http://10.125.121.183:8080/uploadSpring')
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
    }, []);

    return (
        <main>
            <div className='breadcrumbBox'>
                <Breadcrumb />
            </div>
            <div className='boxes'>
                <div className='imageBox'>
                    <img src='http://10.125.121.183:8080/upload/image/하하하.jpg' alt='' />
                </div>
                <div className='informationBox'>
                    <div className='information_coordinates'></div>
                    <div className='information_lists'></div>
                </div>
            </div>
        </main>
    );
}