import React from 'react';
import { useState } from 'react';
import WeatherBlock from './WeatherBlock'; // Component to display the weather
import SensorBlock from './SensorBlock'; // Component to display sensor information
import ControlBlock from './ControlBlock'; // Component to control devices like light, door, and fan


function Dashboard() {
  const [activeSensor, setActiveSensor] = useState(''); // Track which sensor's info to display

  const handleSensorClick = (sensorType) => {
    setActiveSensor(sensorType);
  };

  return (
    <div className="dashboard-container">
      <WeatherBlock /> {/* Component to display today's weather */}
      
      <div className="sensor-buttons">
        {/* Buttons to select which sensor's information to display */}
        <button onClick={() => handleSensorClick('humidity')}>Humidity</button>
        <button onClick={() => handleSensorClick('light')}>Light</button>
        <button onClick={() => handleSensorClick('temperature')}>Temperature</button>
      </div>

      {/* Conditional rendering based on which sensor is active */}
      {activeSensor === 'humidity' && <SensorBlock type="humidity" />}
      {activeSensor === 'light' && <SensorBlock type="light" />}
      {activeSensor === 'temperature' && <SensorBlock type="temperature" />}
      
      <div className="dashboard">
      <ControlBlock deviceType="Light" logo={<img src="light-logo.png" alt="Light" />} onControlClick={() => {}} />
      <ControlBlock deviceType="Door" logo={<img src="door-logo.png" alt="Door" />} onControlClick={() => {}} />
      <ControlBlock deviceType="Fan" logo={<img src="fan-logo.png" alt="Fan" />} onControlClick={() => {}} />
    </div>
    </div>
  );
}

export default Dashboard;