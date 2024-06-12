import './App.css';
import { Page } from './components/page';
import Admin from './components/admin';
import {
  BrowserRouter as Router,
  Route,
  Routes 
} from 'react-router-dom';
import Navbar from './components/navbar';

function App() {
  return (
    <Router>
      <div className="w-screen h-screen overflow-hidden">
        <Navbar />
        <Routes>
          <Route path="/" element={<Page />} />
          <Route path="/admin" element={<Admin isLogged={false} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
