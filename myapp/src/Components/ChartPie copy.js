import '/node_modules/bootstrap/dist/css/bootstrap.min.css';

import React, { Component } from 'react';
import Chart from 'react-google-charts';


const pieOptions = {
    // title: 'My Daily Activities',
    pieHole: 0.4,
    legend: { position: 'bottom' }
}

class ChartPie extends Component {
    render() {

        // props에서 requestedData를 받아옴
        const { requestedData } = this.props;
        console.log("requestedDataPIE: ", requestedData);

        // requestDat가 null인 경우를 처리하여 기본값을 사용하거나 오류 방지
        const box = requestedData ? requestedData.box : 0;
        const bongji = requestedData ? requestedData.bongji : 0;
        const cansnack = requestedData ? requestedData.cansnack : 0;
        const energydrink = requestedData ? requestedData.energydrink : 0;
        const milk = requestedData ? requestedData.milk : 0;

        // requestedData를 사용하여 pieData 생성
        const pieData = [
            ['Task', 'Hours per Day'],
            ['Box', box],
            ['BongJi', bongji],
            ['CanSnack', cansnack],
            ['EnergyDrink', energydrink],
            ['Milk', milk]
        ]

        return (
            <div className='chart-container'>
                {/* <h2>React Donut Chart Example</h2> */}
                <Chart
                    width={'600px'}
                    height={'600px'}
                    chartType='PieChart'
                    loader={<div>Loading Chart</div>}
                    data={pieData}
                    options={pieOptions}
                    rootProps={{ 'data-tested': '3' }}
                />
            </div>
        );
    }
}
export default ChartPie;