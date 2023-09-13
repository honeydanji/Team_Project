import React, { useEffect, useRef } from 'react';
import '../Styles/ChartBar.css';

export default function ChartBar({ requestedData }) {

    console.log("requestedDataBAR: ", requestedData);
    
    const barRef = useRef(null);

    const bestItem = requestedData ? requestedData.bestItem : 0;
    const worstItem = requestedData ? requestedData.worstItem : 0;
        
    useEffect(() => {
        
        const bar = barRef.current;
        let totalItem = bestItem / (bestItem + worstItem) * 100;
        let t = 0;
        bar.style.width = 0;

        const barAnimation = setInterval(() => {
            bar.style.width = t + '%';
            t++ >= totalItem && clearInterval(barAnimation);
        }, 10);

        return () => {
            clearInterval(barAnimation);
        };
    }, [bestItem, worstItem]);

    return (
        <div className='chartBar'>
            <div className="progress-bar" style={{backgroundColor: '#e67474'}}>
                <div id="item-bar">
                    <div ref={barRef} className="progress"></div>
                </div>
            </div>

            <div className='bar_details'>
                <div className='recognized_items'>
                    <div className='decorated_bar_recog'></div>
                    <p style={{fontSize: '30px', fontWeight: 'bold'}}>Well-Recognized<br />Items</p>
                    <p className='value_recog'>
                        {bestItem}
                    </p>
                </div>
                <div className='undefined_items'>
                    <div className='decorated_bar_undef'></div>
                    <p style={{fontSize: '30px', fontWeight: 'bold'}}>Poorly-Recognized<br />Items</p>
                    <p className='value_undef'>
                        {worstItem}
                    </p>
                </div>
            </div>
        </div>
    );
}
