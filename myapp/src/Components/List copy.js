import { useState } from "react";

export default function List({ dateData }) {

  const [uniqueDates, setUniqueDates] = useState([]);
  const [selectedDate, setSelectedDate] = useState(null);
  const [dateDataBySelectedDate, setDateDataBySelectedDate] = useState([]);

  // 서버에서 받은 데이터에서 중복된 날짜를 제거한 목록을 만듦
  const extractUniqueDates = () => {
    const allDates = dateData ? dateData.map((item) => item.date) : []; // dateData에서 날짜만 추출, dateData가 유효한 경우에만 map
    const uniqueDates = Array.from(new Set(allDates)); // 중복 제거
    return uniqueDates;
  };

  // 클릭한 날짜에 해당하는 데이터를 서버에 요청
  const fetchDataByDate = (date) => {
    // 여기에 서버 요청 로직 작성
    // date 매개변수를 서버에 보내고, 해당 날짜에 해당하는 데이터를 받아 옴
    // 서버 응답을 dateDataBySelectedDate 상태로 설정
  };

  // 날짜를 클릭했을 때 처리할 함수
  const handleDateClick = (date) => {
    setSelectedDate(date);
    fetchDataByDate(date);
  };

  // 컴포넌트가 처음 랜더링될 때 중복된 날짜를 추출
  useState(() => {
    const dates = extractUniqueDates();
    setUniqueDates(dates);
  }, []);

  return (
    <div>
      <div className="date-list">
        <h2>Select a Date: </h2>
        <ul>
          {uniqueDates.map((date, index) => (
            <li
              key={index}
              className={date === selectedDate ? 'selected' : ''}
              onClick={() => handleDateClick(date)}
            >
              {date}
            </li>
          ))}
        </ul>
      </div>
    </div>
  )
};