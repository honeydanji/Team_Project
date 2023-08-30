import React, { useEffect, useRef } from 'react';
import '../Styles/ChartBar.css';

export default function ChartBar() {

    const barRef = useRef(null);

    useEffect(() => {
        const bar = barRef.current;
        let totalItem = 80;
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
        <div className='chartBar'>
            <div className="progress-bar">
                <div id="item-bar">
                    <div ref={barRef} className="progress"></div>
                </div>
            </div>

            <div className='bar_details'>
                <div className='recognized_items'>
                    <div className='decorated_bar_recog'></div>
                    <p style={{fontSize: '50px', fontWeight: 'bold'}}>Recognized<br />Items</p>
                    <p className='value_recog'>
                        1,622,632
                    </p>
                </div>
                <div className='undefined_items'>
                    <div className='decorated_bar_undef'></div>
                    <p style={{fontSize: '50px', fontWeight: 'bold'}}>Undefined<br />Items</p>
                    <p className='value_undef'>
                        513,323
                    </p>
                </div>
            </div>
        </div>
    );
}
