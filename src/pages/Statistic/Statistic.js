import React, { useState, useEffect } from 'react';
import SensorChart from '../Chart/Chart';
import Stream from '../Stream/Stream';
const initializeSensorData = (labels) => {
    return labels.map(label => ({
        time: label,
        value: Math.floor(Math.random() * 100) // Initialize with some random values
    }));
};
const Statistic = () => {

    const timeLabels = ['12 AM', '1 AM', '2 AM', '3 AM', '4 AM', '5 AM', '6 AM', '7 AM', '8 AM', '9 AM', '10 AM', '11 AM', '12 PM', '1 PM', '2 PM', '3 PM', '4 PM', '5 PM', '6 PM', '7 PM', '8 PM', '9 PM', '10 PM', '11 PM'];

    const [temperatureData, setTemperatureData] = useState(initializeSensorData(timeLabels));
    const [humidityData, setHumidityData] = useState(initializeSensorData(timeLabels));
    const [lightData, setLightData] = useState(initializeSensorData(timeLabels));

    useEffect(() => {
        const updateSensorData = () => {
            setTemperatureData(data => data.map((item, index) => ({
                ...item,
                value: Math.random() * 5 + 21 // Update with new random value
            })));
            setHumidityData(data => data.map((item, index) => ({
                ...item,
                value: Math.random() * 15 + 40 // Update with new random value
            })));
            setLightData(data => data.map((item, index) => ({
                ...item,
                value: Math.random() * 50 + 300 // Update with new random value
            })));
        };

        const interval = setInterval(updateSensorData, 360000); // Update every 5 seconds

        return () => clearInterval(interval); // Cleanup the interval on component unmount
    }, []);

    return (
        <div className="container mt-auto">
            <div className="row">
                <div className="col-md-8">
                    <SensorChart type="line" data={temperatureData} title="Temperature History" />
                    <SensorChart type="line" data={humidityData} title="Humidity History" />
                    <SensorChart type="line" data={lightData} title="Light History" />
                </div>
                <div className="col-md-4">
                    <Stream />
                </div>
            </div>
        </div>
    );
}

export default Statistic;
