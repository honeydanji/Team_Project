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
          <div className='classesBox'></div>
          <div className='totalItemsBox'></div>
          <div className='correctnessBox'></div>
        </div>
      </div>
    </main>
  );
}