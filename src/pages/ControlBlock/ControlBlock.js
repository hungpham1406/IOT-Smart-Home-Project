import React, { useState } from 'react';
import style from './controlBlock.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear } from '@fortawesome/free-solid-svg-icons';

function ControlBlock({ deviceType, logo, onControlClick, deviceStatus }) {
    const [isExpanded, setExpanded] = useState(false);
    const [timeNotification, setTimeNotification] = useState('');
    const [colorNotification, setColorNotification] = useState('');

    const toggleExpand = () => setExpanded(!isExpanded);

    // Handle saving time settings
    const handleSaveTimeSettings = (e) => {
        e.stopPropagation(); // Prevent event from bubbling up to other handlers
        setTimeNotification('Time settings saved!');
        setTimeout(() => {
            setTimeNotification('');
        }, 3000);  // Clears the notification after 3 seconds
    };
    const getBackgroundClass = (deviceType) => {
        switch (deviceType) {
            case 'Light':
                return style.backgroundLight;
            case 'Door':
                return style.backgroundDoor;
            case 'Fan':
                return style.backgroundFan;
            default:
                return ''; // Default case if no specific background is needed
        }
    };

    // Handle saving color settings
    const handleSaveColorSettings = (e) => {
        e.stopPropagation(); // Prevent event from bubbling up to other handlers
        setColorNotification('Color settings saved!');
        setTimeout(() => {
            setColorNotification('');
        }, 3000);  // Clears the notification after 3 seconds
    };

    const renderDeviceControls = () => {
        switch (deviceType) {
            case 'Light':
                return (
                    <div>
                        <div className={style.lightControls}>
                            <label className={style.timeLabel}>On at: <input type="time" defaultValue="03:02"/></label>
                            <label className={style.timeLabel}>Off at: <input type="time" defaultValue="23:00"/></label>
                            <button className={style.saveButton} onClick={handleSaveTimeSettings}>Save</button>
                            {timeNotification && <div className={style.notification}>{timeNotification}</div>}
                        </div>
                        <div className={style.colorControl}>
                            <label>Color: <input type="color"/></label>
                            <button className={style.saveButton} onClick={handleSaveColorSettings}>Save</button>
                            {colorNotification && <div className={style.notification}>{colorNotification}</div>}
                        </div>
                    </div>
                );
            case 'Door':
                return (
                    <div className={style.doorControls}>
                        <div className={style.doorControlRow}>
                            <p>Manual mode</p>
                            <label className={style.labelButton} onClick={() => onControlClick('manual')}>
                                <input className={style.inputType} type="checkbox"/>
                                <div className={style.movingCircle}></div>
                            </label>
                        </div>
                        <div className={style.doorControlRow}>
                            <p>Auto mode</p>
                            <label className={style.labelButton} onClick={() => onControlClick('auto')}>
                                <input className={style.inputType} type="checkbox"/>
                                <div className={style.movingCircle}></div>
                            </label>
                        </div>
                    </div>
                );
            case 'Fan':
                return (
                    <div className={`${style.fanControls}`}>
                        <label>Wind strength:</label>
                        <input type="range" className={`${style.fanSlider}`} min="1" max="10" defaultValue="5"/>
                    </div>
                );
            default:
                return <p>No specific controls for this device.</p>;
        }
    };

    return (
        <div className={`${style.controlBlock} ${getBackgroundClass(deviceType)}`}>
            <div className={style.upper}>
                <div className={style.deviceLogo}>
                    {logo}
                </div>
                <div className={style.deviceControl}>
                    <label className={style.labelButton} onClick={onControlClick}>
                        <input className={style.inputType} type="checkbox" />
                        <div className={style.movingCircle}></div>
                    </label>
                </div>
            </div>
            <div className={style.deviceType}>
                <p>{deviceType}</p>
            </div>
            <div className={style.deviceSettings}>
                <FontAwesomeIcon icon={faGear} size="1x" />
                <button className={style.setting} onClick={toggleExpand}>More</button>
            </div>
            {isExpanded && (
                <div className={style.expandedContent}>
                    {renderDeviceControls()}
                </div>
            )}
        </div>
    );

}

export default ControlBlock;
