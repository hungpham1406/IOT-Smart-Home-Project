import React, { useState } from 'react';
import WeatherBlock from '../WeatherBlock/WeatherBlock'; // Component to display the weather
import ControlBlock from '../ControlBlock/ControlBlock';
import LCDChangeState from '../LCDChangeState/LCDChangeState';
import '../../index.css';
import style from './dashboard.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLightbulb, faDoorOpen, faFan } from '@fortawesome/free-solid-svg-icons';

function Dashboard() {
    // Static device data, assuming it does not need to be updated dynamically
    const devices = [
        { id: 1, deviceType: 'Light', logo: <FontAwesomeIcon icon={faLightbulb} size="1x"/>, status: 'On' },
        { id: 2, deviceType: 'Door', logo: <FontAwesomeIcon icon={faDoorOpen} size="1x"/>, status: 'Closed' },
        { id: 3, deviceType: 'Fan', logo: <FontAwesomeIcon icon={faFan} size="1x"/>, status: 'Off' }
    ];
    const [expandedId, setExpandedId] = useState(null);

    const handleControlClick = (id) => {
        setExpandedId(expandedId === id ? null : id); // Toggle expansion or collapse the block
    };
    const toggleExpand = (id) => {
        setExpandedId(expandedId === id ? null : id); // Toggle expansion or collapse the block
    };
    return (
        <div className={style.dashboard}>
            <WeatherBlock/> {/* Component to display today's weather */}

            <div className={style.dashboard_container}>
                {devices.map((item) => (
                    <ControlBlock
                        key={item.id}
                        deviceType={item.deviceType}
                        logo={item.logo}
                        onControlClick={() => handleControlClick(item.id)}
                        onToggleExpand={() => toggleExpand(item.id)}
                        deviceStatus={item.status}
                        isExpanded={expandedId === item.id} // Pass whether this block is expanded
                    />
                ))}

            </div>
            <LCDChangeState/>
        </div>
    );
}

export default Dashboard;
