import './App.css';
import { Page } from './components/page';

function App() {
  return (
    <div className="fixed top-0 left-0 right-0 bottom-0 overflow-hidden bg-black">
      <div className="h-screen w-screen m-0 p-1">
        <Page />
      </div>
    </div>
  );
}

export default App;
