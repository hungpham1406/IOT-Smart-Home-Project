import React from 'react';

function AboutUs() {
  return (
    <div className="container my-5">
      <h1>About Us</h1>
      <p>Welcome to our Smart Home project. We are dedicated to providing the best experience for controlling and monitoring your home environment. Our innovative solutions make home automation easy and accessible for everyone.</p>
      
      <section className="my-4">
        <h2>Our Mission</h2>
        <p>Our mission is to create a smart home ecosystem that offers convenience, efficiency, and security, helping to enhance the quality of life for our users.</p>
      </section>
      
      <section className="my-4">
        <h2>Our Team</h2>
        <p>We are a team of passionate engineers, designers, and thinkers who believe in the power of technology to transform daily living.</p>
      </section>
      
      <section className="my-4">
        <h2>Contact Us</h2>
        <p>If you have any questions or feedback, please don't hesitate to reach out to us:</p>
        <ul>
          <li>Email: contact@smarthomeproject.com</li>
          <li>Phone: +123456789</li>
          <li>Address: 123 Smart Home Street, Innovation City</li>
        </ul>
      </section>
    </div>
  );
}

export default AboutUs;
