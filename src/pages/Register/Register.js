import React, { useState } from 'react';
import '../../index.css';

function Register() {
    const [state, setState] = useState({
        email: '',
        password: '',
        confirmPassword: '',
    });
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setState({
            ...state,
            [e.target.name]: e.target.value,
        });
    };

    const validate = () => {
        let validationErrors = {};

        if (!state.email) {
            validationErrors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(state.email)) {
            validationErrors.email = 'Email is invalid';
        }

        if (!state.password) {
            validationErrors.password = 'Password is required';
        } else if (state.password.length < 6) {
            validationErrors.password = 'Password must be at least 6 characters';
        }

        if (!state.confirmPassword) {
            validationErrors.confirmPassword = 'Confirm Password is required';
        } else if (state.password !== state.confirmPassword) {
            validationErrors.confirmPassword = 'Passwords do not match';
        }

        setErrors(validationErrors);
        return Object.keys(validationErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validate()) {
            console.log('Form is valid');
            // Handle form submission logic here
        } else {
            console.log('Form is invalid');
        }
    };

    return (
        <div className="container register-container">
            <form className="register-form" onSubmit={handleSubmit}>
                <h1 className="mb-5 font-weight-light text-uppercase regis-heading">Register</h1>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                        value={state.email}
                        onChange={handleChange}
                    />
                    {errors.email && <div className="text-danger">{errors.email}</div>}
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        name="password"
                        value={state.password}
                        onChange={handleChange}
                    />
                    {errors.password && <div className="text-danger">{errors.password}</div>}
                </div>
                <div className="mb-3">
                    <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
                    <input
                        type="password"
                        className="form-control"
                        id="confirmPassword"
                        name="confirmPassword"
                        value={state.confirmPassword}
                        onChange={handleChange}
                    />
                    {errors.confirmPassword && <div className="text-danger">{errors.confirmPassword}</div>}
                </div>
                <button type="submit" className="btn btn-primary text-right">Register</button>
            </form>
        </div>
    );
}

export default Register;
