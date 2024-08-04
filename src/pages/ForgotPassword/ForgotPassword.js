import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './ForgotPassword.css';
import '../../index.css';

import 'bootstrap/dist/css/bootstrap.min.css';

function ForgotPassword() {
    const [email, setEmail] = useState('');
    const [errors, setErrors] = useState({});

    const validate = () => {
        let validationErrors = {};

        if (!email) {
            validationErrors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            validationErrors.email = 'Email is invalid';
        }

        setErrors(validationErrors);
        return Object.keys(validationErrors).length === 0;
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (validate()) {
            console.log('Reset link sent to', email);
            // Password reset logic here
        } else {
            console.log('Form is invalid');
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center forgot-password-container">
            <form className="forgot-password-form text-center"  onSubmit={handleSubmit}>
                <h1 className="mb-5 font-weight-light text-uppercase">Forgot Password</h1>
                <div className="form-group">
                    <input
                        type="email"
                        className="form-control rounded-pill form-control-lg"
                        placeholder="Email address"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    {errors.email && <div className="text-danger">{errors.email}</div>}
                </div>
                <button type="submit" className="btn btn-primary btn-block rounded-pill shadow-sm">Send Reset Link</button>
                <div className="forgot-password-links mt-5">
                    <Link to="/login" className="text-reset">Back to Login</Link>
                </div>
            </form>
        </div>
    );
}

export default ForgotPassword;
