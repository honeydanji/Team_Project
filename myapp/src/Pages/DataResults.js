import { useEffect, useState } from 'react';
import ChartBar from '../Components/ChartBar';
import ChartDonut from '../Components/ChartDonut';
import ChartPie from '../Components/ChartPie';
import Nav from '../Components/Nav';
import SelectDate from '../Components/SelectDate';
import '../Styles/DataResults.css'
import { useLocation } from 'react-router-dom';
import jwtDecode from "jwt-decode";

export default function DataResults() {

  const [dateList, setDateList] = useState([]);
  const location = useLocation();
  const requestedData = location.state?.requestedData; 

  const token = localStorage.getItem('token');
  // 토큰 추출 및 이름 저장
  // token decoding
  const splitToken = token.split(" ")[1];
  const decodedToken = jwtDecode(splitToken);
  const extractedName = decodedToken.name;

  useEffect(() => {
    const token = localStorage.getItem('token');

    fetch('http://10.125.121.183:8080/history', {
      method: 'GET',
      headers: {
        'authorization': `${token}`,
        'Content-Type': 'application/json',
      },
    })
    .then((res) => res.json())
    .then((data) => {
      console.log("raw Data: ", data)

      const keys = Object.keys(data);      
      setDateList(keys);
      console.log("dateList: ", keys);

    })
    .catch((error) => console.error('데이터 불러오기 오류: ', error));   
  }, [])

  return (
    <main>
       <Nav />      
      <div className="frame">
        {/* HEADER */}
        <div className="header">
          <img className="header_logo" src="/images/darelogo.jpg" alt="" />
          <div className='header_title'>
            <p className='header_mainTitle'> Data Results </p>
            <p className='header_subTitle'>
              <span>Welcome!</span>
              <span style={{ textDecoration: 'underline', textDecorationThickness: '1.5px' }}>{extractedName}</span>
            </p>
          </div>
          <div className='header_selectDate'>
            <SelectDate dateList={dateList} />
          </div>
        </div>

        {/* CONTENT */}
        <div className='content'>
          <div className='classesBox'>
            <h1>Classes</h1>            
            <div className='chartPie'>
              <ChartPie requestedData={requestedData}/>
            </div>
          </div>
          <div className='totalItemsBox'>
            <h1>Total Items</h1>
            <div className='chartBar'>
              <ChartBar requestedData={requestedData} />
            </div>
          </div>
          <div className='correctnessBox'>
            <h1>Correctness</h1>
            <div className='chartDonut'>
              <ChartDonut requestedData={requestedData} />
            </div>
          </div>
        </div>
      </div>
     
    </main>
  );
}