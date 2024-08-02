import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    LineElement,
    PointElement,
    LineController,
    BarController,
    Title,
    Tooltip,
    Legend
} from 'chart.js';

import {Line, Pie} from 'react-chartjs-2';
ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    LineElement,
    PointElement,
    LineController,
    BarController,
    Title,
    Tooltip,
    Legend
);

const SensorChart = ({ data = [],type, title }) => {
    const chartOptions = {
        responsive: true,
        plugins: {
            legend: {
                positions: 'top',
            },
            title: {
                display: true,
                text: title,
            },
        },
    };

    const chartData = {
        labels: data.map(item => item.time),
        datasets: [
            {
                label: title,
                data: data.map(item => item.value),
                borderColor: 'rgba(75,192,192,1)',
                backgroundColor: 'rgba(75,192,192,0.2)',
            },
        ],
    };

    return (
        <div key={title}>
            {type === 'line' ? (
                <Line data={chartData} options={chartOptions} />
            ) : (
                <Pie data={chartData} options={chartOptions} />
            )}
        </div>
    );
};

export default SensorChart;
