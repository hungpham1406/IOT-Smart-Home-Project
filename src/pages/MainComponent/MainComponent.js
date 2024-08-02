import React from 'react';
import Carousel from 'react-bootstrap/Carousel';
import './MainComponent.css';  // Ensure you create a corresponding CSS file for styles

// Import images - make sure these paths are correct based on your project structure
import heroImage from '../../img/homepage/background.png'; // Updated path
import efficiencyImage from '../../img/homepage/background1.png'; // Updated path
import securityImage from '../../img/homepage/background2.png'; // Updated path
import convenienceImage from '../../img/homepage/background3.png'; // Updated path
import testimonialImage from '../../img/homepage/background4.png'; // Updated path


function MainComponent() {
    return (
        <main className="container-fluid ">
            <section className="hero">
                <Carousel>
                    <Carousel.Item>
                        <img src={heroImage} alt="Smart Home" className="d-block w-100 hero-img"/>
                        <Carousel.Caption>
                            <h1 className="display-4">Welcome to Smart Home Solutions</h1>
                            <p className="lead">Experience the convenience of a fully automated home.</p>
                            <button className="btn btn-primary btn-lg">Learn More</button>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img src={efficiencyImage} alt="Efficiency" className="d-block w-100 hero-img"/>
                        <Carousel.Caption>
                            <h3>Efficiency</h3>
                            <p>Reduce energy costs with automated light and climate control.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img src={securityImage} alt="Security" className="d-block w-100 hero-img"/>
                        <Carousel.Caption>
                            <h3>Security</h3>
                            <p>Keep your home safe with smart security cameras and alarm systems.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img src={convenienceImage} alt="Convenience" className="d-block w-100 hero-img"/>
                        <Carousel.Caption>
                            <h3>Convenience</h3>
                            <p>Control your entire home from your smartphone or voice assistant.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img src={testimonialImage} alt="Happy Customer" className="d-block w-100 hero-img"/>
                        <Carousel.Caption>
                            <blockquote>"Since switching to Smart Home, managing my home devices has never been easier. Highly recommend!"</blockquote>
                            <cite>– Jane Doe</cite>
                        </Carousel.Caption>
                    </Carousel.Item>
                </Carousel>
            </section>

            <section className="call-to-action mt-5 text-center">
                <h2>Ready to Transform Your Home?</h2>
                <button className="btn btn-success btn-lg">Get Started</button>
            </section>
        </main>
    );
}

export default MainComponent;
