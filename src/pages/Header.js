// Header.js
import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Dashboard from './Dashboard';
import AboutUs from './AboutUs';
import MainComponent from './MainComponent';

function Header() {
  return (
    <div>

      <header className="bg-light py-3">
        <div className="container">
          <div className="row align-items-center">
            <div className="col">
              <h1>Logo + tên nhóm/sản phẩm</h1>
            </div>
            <div className="col">
              <nav className="nav justify-content-end">
                <Link className="nav-link active" href="/">Home</Link>
                <Link className="nav-link" href="/dashboard">Dashboard</Link>
                <Link className="nav-link" href="/about-us">About us</Link>
                <Link className="nav-link" href="/login">Login</Link>
              </nav>
            </div>
          </div>
        </div>
      </header>
      <MainComponent />
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/login" element={<Login />} />
        <Route path="/about-us" element={<AboutUs />} />
      </Routes>
    </div>
  );
}

export default Header;
