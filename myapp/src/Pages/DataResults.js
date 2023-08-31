import ChartBar from '../Components/ChartBar';
import ChartDonut from '../Components/ChartDonut';
import ChartPie from '../Components/ChartPie';
import SelectDate from '../Components/SelectDate';
import '../Styles/DataResults.css'

export default function DataResults() {

  return (
    <main>
      <div className="frame">
        {/* HEADER */}
        <div className="header">
          <img className="header_logo" src="/images/darelogo.jpg" alt="" />
          <div className='header_title'>
            <p className='header_mainTitle'> Data Results </p>
            <p className='header_subTitle'>
              <span>Welcome!</span>
              <span style={{ textDecoration: 'underline', textDecorationThickness: '1.5px' }}>Joe Wonjun</span>
            </p>
          </div>
          <div className='header_selectDate'>
            <SelectDate />
          </div>
        </div>

        {/* CONTENT */}
        <div className='content'>
          <div className='classesBox'>
            <h1>Classes</h1>            
            <div className='chartPie'>
              <ChartPie />
            </div>
          </div>
          <div className='totalItemsBox'>
            <h1>Total Items</h1>
            <div className='chartBar'>
              <ChartBar />
            </div>
          </div>
          <div className='correctnessBox'>
            <h1>Correctness</h1>
            <div className='chartDonut'>
              <ChartDonut />
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}