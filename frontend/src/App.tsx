import './App.css';
import { Page } from './components/page';
import Admin from './components/admin';
import Navbar from './components/navbar';
import Tests from './components/tests';
import {
  BrowserRouter as Router,
  Route,
  Routes 
} from 'react-router-dom';
import { useState } from 'react';

function App() {

  const [isLogged, setIsLogged] = useState(false);

  return (
    <Router>
      <div className="w-screen h-screen overflow-hidden">
        <Navbar />
        <Routes>
          <Route path="/" element={<Page />} />
          <Route path="/admin" element={<Admin isLogged={false} />} />
          <Route path="/tests" element={<Tests />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
