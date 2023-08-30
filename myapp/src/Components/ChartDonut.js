import React, { useEffect } from 'react';
import '../Styles/ChartDonut.css';

export default function ChartDonut() {
    const correctness = 70.5; // 예시 값. 실제 값을 할당해야 합니다.

    useEffect(() => {
        const donut = document.querySelector(".donut");
        if (!donut) {
            return; // 요소를 찾지 못한 경우 종료
        }

        donut.dataset.percent = correctness;
        donut.style.background = `conic-gradient(#3F8BC9 0% ${correctness}%, #F2F2F2 ${correctness}% 100%)`;

        let t = 0;
        const donutAnimation = setInterval(() => {
            donut.dataset.percent = t;
            donut.style.background = `conic-gradient(#4F98FF 0 ${t}%, #DEDEDE ${t}% 100%)`;

            t++ >= correctness && clearInterval(donutAnimation);
        }, 10);

        return () => {
            clearInterval(donutAnimation); // 컴포넌트가 언마운트되면 애니메이션 중지
        };
    }, []); // 빈 배열은 컴포넌트가 마운트된 후 한 번만 실행하도록 함

    return (
        <div className='donut' data-percent={correctness}></div>
    );
}
