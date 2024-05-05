// WeatherBlock.js
import React, { useState, useEffect } from 'react';

function WeatherBlock() {
  const [weather, setWeather] = useState(null);

  useEffect(() => {
    // You would replace this URL with the endpoint from your weather API provider
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
  }, []); // The empty array causes this effect to only run once on component mount

  if (!weather) {
    return <div>Loading weather data...</div>;
  }

    return (
        <div className="weather-block-container">
            <div className="weather-block">
                <h2>Today's Weather</h2>
                {/* Display the main weather data, you'll need to adjust the fields based on your API's response structure */}
                <p>{weather.name}: {weather.main.temp}°C</p>
                <p>Humidity: {weather.main.humidity}%</p>
                <p>Wind: {weather.wind.speed} km/h</p>
                {/* Add additional weather information as needed */}
            </div>
        </div>
  );
}

export default WeatherBlock;
