import React, { useState, useEffect, useRef } from 'react';
import './Stream.css'; // Ensure this path is correct

const Stream = () => {
    const [updates, setUpdates] = useState([]);
    const streamMessagesRef = useRef(null);  // Ref for the messages container

    const scrollToBottom = () => {
        if (streamMessagesRef.current) {
            streamMessagesRef.current.scrollTop = streamMessagesRef.current.scrollHeight;
        }
    };

    useEffect(() => {
        const sensors = [
            { label: 'Temperature', value: () => `${(Math.random() * 5 + 20).toFixed(1)}°C` },
            { label: 'Humidity', value: () => `${(Math.random() * 10 + 40).toFixed(1)}%` },
            { label: 'Light Level', value: () => `${(Math.random() * 50 + 300).toFixed(1)} Lux` },
            { label: 'Door Status', value: () => Math.random() > 0.5 ? "Open" : "Closed" },
            { label: 'Fan Status', value: () => Math.random() > 0.5 ? "On" : "Off" },
            { label: 'Light Status', value: () => Math.random() > 0.5 ? "On" : "Off" }
        ];

        let index = 0;  // Index to track the current sensor being updated

        const scheduleNextUpdate = () => {
            const delay = 2000; // 2 seconds delay

            if (index >= sensors.length) {
                index = 0; // Reset the index to start from the first sensor again
            }

            setTimeout(() => {
                const sensor = sensors[index];
                const newUpdate = {
                    label: sensor.label,
                    value: sensor.value(),
                    timestamp: new Date().toLocaleTimeString()
                };
                setUpdates(updates => [...updates, newUpdate]);
                scrollToBottom();

                index++;  // Move to the next sensor
                scheduleNextUpdate();  // Schedule the update for the next sensor
            }, delay);
        };

        scheduleNextUpdate(); // Start the sequence

        return () => {
            // Cleanup the timeout when component unmounts
            clearTimeout();
        };
    }, []);

    return (
        <div className="stream-container">
            <h2 className="stream-header">Stream</h2>
            <div ref={streamMessagesRef} className="stream-messages">
                {updates.map((update, idx) => (
                    <div key={idx} className="stream-message">
                        <p><strong>{update.timestamp}</strong></p>
                        <p>{update.label}: {update.value}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Stream;