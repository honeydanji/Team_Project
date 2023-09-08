import { useEffect, useState } from "react";
import Modals from "./Modals";

// const test = [
//   {
//     date: '2023-09-01',   
//   },
//   {
//     date: '2023-09-02',   
//   },
//   {
//     date: '2023-09-03',   
//   },
//   {
//     date: '2023-09-04',   
//   },
//   {
//     date: '2023-09-05',   
//   },
//   {
//     date: '2023-09-06',   
//   },
//   {
//     date: '2023-09-07',   
//   },
//   {
//     date: '2023-09-08',   
//   },
//   {
//     date: '2023-09-09',   
//   },
//   {
//     date: '2023-09-10',   
//   },    
// ]

export default function List({ dateData }) {

  const uniqueDates = dateData ? [...new Set(Object.values(dateData))] : [];
  const [selectedItem, setSelectedItem] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가
  const [selectedItemData, setselectedItemData] = useState([]);

  const handleItemClick = (item) => {
    setSelectedItem(item === selectedItem ? null : item);
    if(item === selectedItem) {
      // 이미 선택된 항목을 클릭하면 모달을 닫음
      setSelectedItem(null);
      setIsModalOpen(false);
    } else {
      setSelectedItem(item);
      setIsModalOpen(true);      
    }
  };

  // useEffect를 사용하여 selectedItem이 변경될 때 데이터를 가져옴
  useEffect(() => {
    
    if (selectedItem) {
      const serverURL = `http://10.125.121.183:8080/history/${selectedItem}`;
      const token = localStorage.getItem('token');      
  
      fetch(serverURL, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'authorization': `${token}`,
        },
      })
        .then((response) => {
          // 서버의 응답이 정상인지 확인
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          // fetch 함수의 HTTP응답을 Json으로 변환하고 반환
          return response.json();
        })
        .then((data) => {
          //서버 응답을 처리하는 코드 추가
          console.log("서버 응답 데이터: ", data);
          setselectedItemData(data); // 서버로부터 받은 선택된 항목의 데이터를 저장          
        })
        .catch((error) => {
          // 오류가 발생한 경우 오류를 처리
          console.error('오류 발생: ', error);
        });
    }
  }, [selectedItem]) // selectedItem이 변경될 때만 useEffect 실행

  return (
    <>
      <ul className="divide-y divide-gray-100 pr-8 overflow-y-scroll h-30" style={{ height: '570px' }}>
        {uniqueDates.map((item, index) => (
          <li
            key={index}
            className={`flex justify-between gap-x-6 py-1 px-9 ${selectedItem === item ? 'bg-gray-100' : ''}`}
            onClick={() => handleItemClick(item)}
            style={{ cursor: 'pointer' }}
          >
            <div className="flex min-w-0 gap-x-4">
              <div className="min-w-0 flex-auto">
                <p className="text-lg font-semibold leading-6 text-gray-900">
                  {item}
                </p>
              </div>
            </div>
          </li>
        ))}
      </ul>

      {/* 모달 컴포넌트를 조건부로 랜더링 */}
      {isModalOpen && (
        <Modals
          selectedItemData={selectedItemData}
          selectedItem={selectedItem}
          onClose={() => setIsModalOpen(false)} // 모달을 닫는 함수        
        />
      )}
    </>
  );
}