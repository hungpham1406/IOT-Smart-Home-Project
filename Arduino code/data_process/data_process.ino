#include <Wire.h>

void setup()
{
  Wire.begin(0x04);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
  Serial.begin(9600);           // start serial for output
}

void loop()
{
  String data = Serial.readStringUntil('\n'); // Read data from serial until newline character

  // Send the received data to STM32 via I2C
  Wire.beginTransmission(0x00); // Start transmission to STM32 (assuming STM32 is at address 0x08)
  Wire.write(data.c_str());     // Send data as a char array
  Wire.endTransmission();       // End transmission
  delay(100);
}


void receiveEvent(int howMany)
{
  while(0 < Wire.available()) // loop through all but the last
  {
    char c = Wire.read(); // receive byte as a character
    Serial.print(c);
  }
Serial.print('\n'); 
}
// Wire Slave Receiver
// by Nicholas Zambetti <http://www.zambetti.com>