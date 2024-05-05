
// App.js

// ################################## MAIN ##############################
import React from 'react';
// import Header from './pages/Header';
import { Routes, Route, Link } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import AboutUs from './pages/AboutUs';
import MainComponent from './pages/MainComponent';
import Register from './pages/Register';

// import Dashboard from './pages/Dashboard';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <header className="bg-light py-3">
        <div className="container">
          <div className="row align-items-center">
            <div className="col">
              <h1>Logo + tên nhóm/sản phẩm</h1>
            </div>
            <div className="col">
              <nav className="nav justify-content-end">
                <Link className="nav-link active" to="/">Home</Link>
                <Link className="nav-link" to="/dashboard">Dashboard</Link>
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
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/about-us" element={<AboutUs />} />
      </Routes>
    </div>
  );
}

// ###############################END MAIN ##############################




export default App;
