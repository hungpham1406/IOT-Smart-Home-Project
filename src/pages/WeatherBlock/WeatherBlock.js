import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import styles from './weatherBlock.module.css';
import { faCloud, faSun, faCloudRain, faBolt, faSnowflake, faSmog, faThermometerHalf, faTint } from '@fortawesome/free-solid-svg-icons';

const AIO_KEY = 'aio_Tnyw5684RNcrpwBzGRjUKodEoDX2'; // Your Adafruit IO key
const AIO_USERNAME = 'hungpham1406'; // Your Adafruit IO username

async function getFeedData(feedKey) {
    const url = `https://io.adafruit.com/api/v2/${AIO_USERNAME}/feeds/${feedKey}/data/last`;
    try {
        const response = await fetch(url, {
            headers: {
                'X-AIO-Key': AIO_KEY
            }
        });
        if (!response.ok) {
            throw new Error(`HTTP status ${response.status}: ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching data from Adafruit IO:', error);
        throw error;
    }
}

function WeatherBlock() {
    const [weather, setWeather] = useState(null);
    const [activeSensor, setActiveSensor] = useState('');
    const [sensorData, setSensorData] = useState(null);

    const handleSensorClick = async (sensorType) => {
        setActiveSensor(sensorType); // Set active sensor regardless of outcome
        if (sensorType) {
            const feedKey = sensorType + 'stm32';
            try {
                const data = await getFeedData(feedKey);
                setSensorData(data);

            } catch (error) {
                console.error('Failed to fetch sensor data:', error);
                setSensorData(null);
            }
        } else {
            setSensorData(null);
        }
    };

    useEffect(() => {
        const apiUrl = 'https://api.openweathermap.org/data/2.5/weather?q=Ho Chi Minh&appid=5695b08ace5c2563a7a8023f9fdb9441&units=metric';
        const fetchData = async () => {
            try {
                const response = await fetch(apiUrl);
                const data = await response.json();
                setWeather(data);
            } catch (error) {
                console.error('Error fetching weather data:', error);
            }
        };
        fetchData();
    }, []);

    if (!weather) {
        return <div>Loading weather data...</div>;
    }
    const getBackgroundClass = (condition) => {
        const conditionMap = {
            Clear: 'sunny',
            Clouds: 'cloudy',
            Rain: 'rainy',
            Thunderstorm: 'thunderstorm',
            Snow: 'snow',
            Mist: 'mist',
        };
        return styles[conditionMap[condition] || 'default'];
    };
    const getWeatherIcon = (condition) => {
        const map = {
            Clear: faSun,
            Clouds: faCloud,
            Rain: faCloudRain,
            Thunderstorm: faBolt,
            Snow: faSnowflake,
            Mist: faSmog,
        };
        return map[condition] || faCloud;
    };
    const getIconStyle = (condition) => {
        const styleMap = {
            Clear: { color: '#f0c330' }, // Sunny
            Clouds: { color: '#B0BEC5' }, // Cloudy
            Rain: { color: '#2196F3' }, // Rainy
            Thunderstorm: { color: '#4A148C' }, // Thunderstorm
            Snow: { color: '#ECEFF1' }, // Snow
            Mist: { color: '#78909C' } // Mist
        };
        return styleMap[condition] || { color: '#333' }; // Default color
    };

    return (
        <div className={styles.weatherBlockContainer}>
            <div className={`${styles.weatherBlock} ${getBackgroundClass(weather.weather[0].main)}`}>
                {sensorData ? (
                    <div>
                        <h2>{activeSensor}</h2>
                        <p>{sensorData.value}</p>
                    </div>
                ) : (
                    <>
                        <h2>Today's Weather in {weather.name}</h2>
                        <FontAwesomeIcon icon={getWeatherIcon(weather.weather[0].main)} size="3x"
                                         style={getIconStyle(weather.weather[0].main)}/>
                        <p>{weather.weather[0].main}: {weather.main.temp}°C</p>
                        <p>Wind: {weather.wind.speed} km/h</p>
                    </>
                )}
                <div className={styles.sensorButtons}>
                    <FontAwesomeIcon icon={faTint} size="2x" className={styles.humidityIcon}
                                     onClick={() => handleSensorClick('humid')}/>
                    <FontAwesomeIcon icon={faSun} size="2x" className={styles.lightIcon}
                                     onClick={() => handleSensorClick('led')}/>
                    <FontAwesomeIcon icon={faThermometerHalf} size="2x" className={styles.temperatureIcon}
                                     onClick={() => handleSensorClick('temp')}/>
                </div>
            </div>
        </div>
    );
}

export default WeatherBlock;
