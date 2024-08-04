import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './LCDChangeState.css'; // Create this file for additional styling

function LCDChangeState() {
    const [state, setState] = useState('Display temp, humid, time, date');

    const handleChange = (e) => {
        setState(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('State saved:', state);
        // Add save logic here
    };

    return (
        <div className="container lcd-container">
            <form className="lcd-form" onSubmit={handleSubmit}>
                <h1 className="mb-4">Change State on LCD</h1>
                <div className="form-group">
                    <label htmlFor="stateSelect" className="form-label">State</label>
                    <select
                        id="stateSelect"
                        className="form-control"
                        value={state}
                        onChange={handleChange}
                    >
                        <option>Display temp, humid, time, date</option>
                        <option>Adjust hour, red led blink</option>
                        <option>Adjust minute, yellow led blink</option>
                        <option>Adjust second, yellow led blink</option>
                        <option>Rotate servo</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary mt-3">Save</button>
            </form>
        </div>
    );
}

export default LCDChangeState;
