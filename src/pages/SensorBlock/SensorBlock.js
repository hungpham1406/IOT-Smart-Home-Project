import React from 'react';

function SensorBlock({ type }) {
  let data;

  // Assign some random values for demonstration
  switch (type) {
    case 'humidity':
      data = `${Math.floor(Math.random() * 100)}%`;
      break;
    case 'light':
      data = `${Math.floor(Math.random() * 100)} lux`;
      break;
    case 'temperature':
      data = `${Math.floor(Math.random() * 35)}°C`;
      break;
    default:
      data = 'No data';
  }

  return (
    <div className="sensor-block-container">
      <div className="sensor-block">
        <h3>{type.charAt(0).toUpperCase() + type.slice(1)}</h3>
        <p>Data for {type}: {data}</p>
      </div>
      {/* <div className="sensor-buttons">
        <button onClick={() => {}}>Humidity</button>
        <button onClick={() => {}}>Light</button>
        <button onClick={() => {}}>Temperature</button>
      </div> */}
    </div>
  );
}

export default SensorBlock;
