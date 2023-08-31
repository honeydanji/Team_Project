import React, { useEffect, useState } from "react";
import { Doughnut } from "react-chartjs-2";

export default function Test() {
    const [animatedData, setAnimatedData] = useState({
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: []
        }],
    });

    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        const data = {
            labels: ['Work', 'Eat', 'Commute', 'Watch TV', 'Sleep'],
            datasets: [{
                data: [11, 2, 2, 2, 7],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4CAF50', '#E91E63'] 
            }], 
        };

        const animationInterval = setInterval(() => {
            const newIndex = currentIndex + 1;
            if (newIndex <= data.labels.length) {
                setAnimatedData({
                    labels: data.labels.slice(0, newIndex),
                    datasets: [{
                        data: data.datasets[0].data.slice(0, newIndex),
                        backgroundColor: data.datasets[0].backgroundColor.slice(0, newIndex)
                    }],
                });
                setCurrentIndex(newIndex);
            } else {
                clearInterval(animationInterval);
            }
        }, 500) // 각 데이터 포인트가 0.5초마다 추가됨

        return () => {
            clearInterval(animationInterval);
        };
    }, [currentIndex]);

    return (
        <div>
            <h2>React Donut Chart Example</h2>
            <Doughnut 
                data={animatedData}
                options={{
                    title: {
                        display: true,
                        text: 'My Daily Activites'
                    },
                }}
            />
        </div>
    );
}