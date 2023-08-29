import React, { useEffect, useRef } from 'react';
import './ChartBar.css';

export default function ChartBar() {

    const barRef = useRef(null);

    useEffect(() => {
        const bar = barRef.current;
        let totalItem = 72;
        let t = 0;
        bar.style.width = 0;

        const barAnimation = setInterval(() => {
            bar.style.width = t + '%';
            t++ >= totalItem && clearInterval(barAnimation);
        }, 10);

        return () => {
            clearInterval(barAnimation);
        };
    }, []);

    return (
        <div className="progress-bar">
            <div id="item-bar">
                <div ref={barRef} className="progress"></div>
            </div>
        </div>
    );
}
