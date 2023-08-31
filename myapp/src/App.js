import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom"

import Frontpage from './Pages/Frontpage';
import LogIn from './Pages/LogIn';
import DragDrop from './Pages/DragDrop';
import List from './Components/List';
import SignUp from './Components/SignUp';
import Carousel from './Components/Carousel';
import Stats from './Components/Stats';
import DataResults from './Pages/DataResults';
import ChartBar from './Components/ChartBar';
import ChartDonut from './Components/ChartDonut';
import Test from './Pages/Test';
import Service from './Pages/Service';
import SelectDate from './Components/SelectDate';
import PieChart from './Components/ChartPie';


const CAROUSEL_IMAGES = [
  'https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg',
  'https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg',
  'https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg',
]

export default function App() {

  return (
    <BrowserRouter>
      <main>
        <Routes>
          <Route path='/' element = {<Frontpage />}/>
          <Route path='/login' element = {<LogIn />} />  
          <Route path='/dragdrop' element = {<DragDrop />}/>   
          <Route path='/list' element = {<List />}/>
          <Route path='/signup' element = {<SignUp />}/>
          <Route path='/carousel' element = {<Carousel carouselList={CAROUSEL_IMAGES}/>} />
          <Route path='/test' element = {<Test />} />
          <Route path='/stats' element = {<Stats />} />
          <Route path='/dataresults' element = {<DataResults />} />
          <Route path='/chartbar' element = {<ChartBar />} />
          <Route path='/chartdonut' element = {<ChartDonut />} />
          <Route path='/service' element = {<Service />} />
          <Route path='/selectdate' element = {<SelectDate />}/>
          <Route path='/chartpie' element = {<PieChart/>} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}
