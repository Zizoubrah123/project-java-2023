import LandingPage from "./pages/LandingPage";
import {Routes,Route} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import CustomizePage from "./pages/CustomizePage";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/" element={<LandingPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/custom" element={<CustomizePage/>} />
      </Routes>
      
    </div>
  );
}

export default App;
