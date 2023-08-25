import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom"

import Frontpage from './Components/Frontpage';
import LogIn from './Components/LogIn';
import ServiceMain from './Components/ServiceMain';
import DragDrop from './Components/DragDrop';
import List from './Components/List';
import SignUp from './Components/SignUp';

export default function App() {

  return (
    <BrowserRouter>
      <main>
        <Routes>
          <Route path='/' element = {<Frontpage />}/>
          <Route path='/servicemain' element = {<ServiceMain />} />
          <Route path='/login' element = {<LogIn />} />  
          <Route path='/dragdrop' element = {<DragDrop />}/>   
          <Route path='/list' element = {<List />}/>
          <Route path='/signup' element = {<SignUp />}/>
        </Routes>
      </main>
    </BrowserRouter>
  );
}
