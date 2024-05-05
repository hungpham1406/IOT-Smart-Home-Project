import React, { useState } from 'react';
import '../index.css'; 

function Register() {
    const [state, setState] = useState({
      email: '',
      password: '',
      confirmPassword: '',
    });
  
    const handleChange = (e) => {
      setState({
        ...state,
        [e.target.name]: e.target.value,
      });
    };
  
    const handleSubmit = (e) => {
      e.preventDefault();
      // Handle form submission logic here
    };
    return (
        <div className="container register-container">
            <form className="register-form" onSubmit={handleSubmit}>
            <h1 className="mb-5 font-weight-light text-uppercase regis-heading">Register</h1>
            <div className="mb-3 ">
              <label htmlFor="email" className="form-label">Email address</label>
              <input type="email" className="form-control" id="email" name="email" value={state.email} onChange={handleChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input type="password" className="form-control" id="password" name="password" value={state.password} onChange={handleChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
              <input type="password" className="form-control" id="confirmPassword" name="confirmPassword" value={state.confirmPassword} onChange={handleChange} />
            </div>
            <button type="submit" className="btn btn-primary text-right">Register</button>
          </form>
        </div>
      );
    }
    
export default Register;