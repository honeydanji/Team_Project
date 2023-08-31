import '/node_modules/bootstrap/dist/css/bootstrap.min.css';

import React, { Component } from 'react';
import Chart from 'react-google-charts';

const pieData = [
    ['Task', 'Hours per Day'],
    ['Work', 11],
    ['Eat', 2],
    ['Commute', 2],
    ['Watch TV', 2],
    ['Sleep', 7]
]

const pieOptions = {
    // title: 'My Daily Activities',
    pieHole: 0.4,
    legend: { position: 'bottom'}
}

class PieChart extends Component {
    render() {
        return (
            <div>
                {/* <h2>React Donut Chart Example</h2> */}
                <Chart 
                    width={'1125px'}
                    height={'600px'}
                    chartType='PieChart'
                    loader={<div>Loading Chart</div>}
                    data={pieData}
                    options={pieOptions}
                    rootProps={{'data-tested': '3'}}
                />
            </div>
        );
    }
}
export default PieChart;