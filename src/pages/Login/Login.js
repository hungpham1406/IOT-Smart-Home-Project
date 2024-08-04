import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../../index.css'; // Make sure to create this CSS file


function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [errors, setErrors] = useState({});

  const validate = () => {
    let validationErrors = {};

    if (!username) {
      validationErrors.username = 'Username is required';
    }

    if (!password) {
      validationErrors.password = 'Password is required';
    } else if (password.length < 6) {
      validationErrors.password = 'Password must be at least 6 characters';
    }

    setErrors(validationErrors);
    return Object.keys(validationErrors).length === 0;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (validate()) {
      console.log('Logging in', username, password, rememberMe);
      // Login logic here
    } else {
      console.log('Form is invalid');
    }
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
            {errors.username && <div className="text-danger">{errors.username}</div>}
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
            {errors.password && <div className="text-danger">{errors.password}</div>}
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
            <Link to="/ForgotPassword" className="text-reset">I forgot my password</Link>
            <br />
            <Link to="/Register" className="text-reset">Register</Link>
          </div>
        </form>
      </div>
  );
}

export default Login;
