import React, { useState } from 'react';
import {Link} from 'react-router-dom';
import '../index.css'; // Make sure to create this CSS file

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    // Login logic here
    console.log('Logging in', username, password, rememberMe);
  };

  return (
    <div className="d-flex justify-content-center align-items-center login-container">
      <form className="login-form text-center" onSubmit={handleSubmit}>
        <h1 className="mb-5 font-weight-light text-uppercase">Log in</h1>
        <div className="form-group">
          <input
            type="text"
            className="form-control rounded-pill form-control-lg"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <input
            type="password"
            className="form-control rounded-pill form-control-lg"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="form-check mb-3">
          <input
            type="checkbox"
            className="form-check-input"
            id="rememberMe"
            checked={rememberMe}
            onChange={(e) => setRememberMe(e.target.checked)}
          />
          <label className="form-check-label" htmlFor="rememberMe">Remember me</label>
        </div>
        <button type="submit" className="btn btn-primary btn-block rounded-pill shadow-sm">Log in</button>
        <div className="login-links mt-5">
          <a href="#" className="text-reset">I forgot my password</a>
          <br />
          <Link to="/Register" className="text-reset">Register</Link>
        </div>
      </form>
    </div>
  );
}

export default Login;
