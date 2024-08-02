// ################################## MAIN ##############################
import React from 'react';
// import Header from './pages/Header';
import { Routes, Route, Link } from 'react-router-dom';
import Dashboard from './pages/Dashboard/Dashboard';
import Login from './pages/Login/Login';
import AboutUs from './pages/AboutUs/AboutUs';
import MainComponent from './pages/MainComponent/MainComponent';
import Register from './pages/Register/Register';
import Statistic from './pages/Statistic/Statistic';
import logo from '../src/logo/Frame 1.svg';
import 'bootstrap/dist/css/bootstrap.min.css';
import GlobalStyle from './pages/GlobalStyle';


function App() {
  const style = {
    width: '70px', // Set width as needed
    height: 'auto' // Adjusts height to maintain aspect ratio
  };
  return (
      <GlobalStyle>
        <div className="App">
          <header className="bg-light py-3">
            <div className="container">
              <div className="row align-items-center">
                <div className="col">
                  <img src={logo} alt="Company Logo" style={style}/>
                </div>
                <div className="col">
                  <nav className="nav justify-content-end">
                    <Link className="nav-link active" to="/">Home</Link>
                    <Link className="nav-link" to="/dashboard">Dashboard</Link>
                    <Link className="nav-link" to="/statistic">Statistic</Link>
                    <Link className="nav-link" to="/about-us">About us</Link>
                    <Link className="nav-link" to="/login">Login</Link>
                  </nav>
                </div>
              </div>
            </div>
          </header>

          {/* <MainComponent/> */}
          <Routes>
            <Route path="/" element={<MainComponent />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/statistic" element={<Statistic />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/about-us" element={<AboutUs />} />
          </Routes>
        </div>
      </GlobalStyle>
  );
}

// ###############################END MAIN ##############################




export default App;
