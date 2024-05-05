// ControlBlock.js
import React from 'react';
import '../index.css'; // Make sure to create this CSS file

function ControlBlock({ deviceType, logo, onControlClick }) {
  return (
    <div className="control-block">
      <div className="device-logo">
        {logo}
      </div>
      <div className="device-control">
        <button onClick={onControlClick}>{deviceType}</button>
      </div>
      <div className="device-settings">
        <button onClick={() => {}}>⚙</button>
      </div>
    </div>
  );
}

export default ControlBlock;
