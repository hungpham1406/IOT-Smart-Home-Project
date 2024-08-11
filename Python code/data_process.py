import serial
import random
import time
import sys
import serial
from Adafruit_IO import MQTTClient

AIO_FEEDS = ["button1val", "button2val", "button3val", "rgbstm32"]
AIO_USERNAME = "hungpham1406"
AIO_KEY = "aio_akHq62ZnP5dlZTUCknJgI6oVgRN3"

def connected ( client ):
    print ("Connect Successfully ...")
    for feed in AIO_FEEDS:
        client.subscribe(feed)
        print(f"Subscribed to feed: {feed}")

def subscribe ( client , userdata , mid , granted_qos ):
    print (" Subcribe Successfully ... ")

def disconnected ( client ):
    print (" Disconnect ... ")
    sys.exit (1)

def message ( client , feed_id , payload ):
    print(f"Received data from {feed_id}: {payload}")
    if feed_id == "button1val":
        ser.write(f"btn1\n".encode())
    if feed_id == "button2val":
        ser.write(f"btn2\n".encode())
    if feed_id == "button3val":
        ser.write(f"btn3\n".encode())
        # print(f"Button {payload} \n")

    if feed_id == "rgbstm32":
        print(payload)


client = MQTTClient ( AIO_USERNAME , AIO_KEY )
client.on_connect = connected
client.on_disconnect = disconnected
client.on_message = message
client.on_subscribe = subscribe
client.connect ()
client.loop_background ()

ser = serial.Serial('COM4', 9600, timeout=1)
time.sleep(2)  # wait for the connection to establish

while True :
    # Set up the serial connection (The COM port may vary, check your Arduino IDE for the correct port)
    # ser = serial.Serial('COM4', 9600, timeout=1)  # Replace 'COM3' with your actual COM port
    # time.sleep(2)  # wait for the connection to establish
    try:
        while True:
            if ser.in_waiting > 0:
                line = ser.readline().decode('utf-8').rstrip()  # Read a line from the Arduino output, data will have format: "!Data#value!"
                print("Receive from stm32: ", line)  # Print the line received from Arduino
                if(line[0] == '!'):
                    data = line.split('!')[1].split('#')[0]
                    value = float(line.split('#')[1].split('!')[0])  # Extract the value from the line
                    print ("Update value: ", value )
                    if(data == "Temp"):
                        client.publish ("tempstm32", value )
                    elif(data == "Humi"):
                        client.publish ("humidstm32", value )
                    time.sleep(1)  # Delay for 1 second before next reading
    except KeyboardInterrupt:
        print("Program exited")
        sys.exit(0)
    finally:
        ser.close()  # Close serial port when done