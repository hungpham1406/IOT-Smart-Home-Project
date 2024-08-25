/*
 * button.c
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#include "button.h"
extern I2C_HandleTypeDef hi2c2;

char i2cRxBuffer[25];

int KeyReg0[NUMBER_BUTTONS];
int KeyReg1[NUMBER_BUTTONS];
int KeyReg2[NUMBER_BUTTONS];
int KeyReg3[NUMBER_BUTTONS];

int timerForKeyPress[NUMBER_BUTTONS];
int button_flag[NUMBER_BUTTONS];

uint16_t gpio_pin[NUMBER_BUTTONS] = {button1_Pin, button2_Pin, button3_Pin};
GPIO_TypeDef* gpio_port[NUMBER_BUTTONS] = {button1_GPIO_Port, button2_GPIO_Port, button3_GPIO_Port};

void initStateForButton() {
	for (int i = 0; i < NUMBER_BUTTONS; ++i) {
	    KeyReg0[i] = NORMAL_STATE;
	    KeyReg1[i] = NORMAL_STATE;
	    KeyReg2[i] = NORMAL_STATE;
	    KeyReg3[i] = NORMAL_STATE;
	    timerForKeyPress[i] = 200;
	    button_flag[i] = 0;
	 }
}

int isButtonPressed(int key_index) {
	if(button_flag[key_index] == 1) {
		button_flag[key_index] = 0;
		return 1;
	}
	return 0;
}

void subKeyProcess(int key_index) {
	button_flag[key_index] = 1;
}

extern void rgb_color(uint8_t red, uint8_t green, uint8_t blue);

void getKeyInput() {
	for(int i = 0; i<NUMBER_BUTTONS; i++) {
		KeyReg0[i] = KeyReg1[i];
		KeyReg1[i] = KeyReg2[i];
		KeyReg2[i] = HAL_GPIO_ReadPin(gpio_port[i], gpio_pin[i]);
		if((KeyReg0[i] == KeyReg1[i]) && (KeyReg1[i] == KeyReg2[i])) {
			if(KeyReg3[i] != KeyReg2[i]) {	// Press and release
				KeyReg3[i] = KeyReg2[i];
				if(KeyReg2[i] == PRESSED_STATE) {
					//TO DO
					subKeyProcess(i);
					timerForKeyPress[i] = 200;
				}
			}
			else {		// Press and hold
				timerForKeyPress[i]--;
				if(timerForKeyPress[i] <= 0) {
					//TO DO
					if(KeyReg2[i] == PRESSED_STATE) {
						subKeyProcess(i);
					}
					timerForKeyPress[i] = 200;
				}
			}
		}
	}
}

void getKeyFromAdafruit()
{
	HAL_I2C_Slave_Receive(&hi2c2, (uint8_t *)i2cRxBuffer, sizeof(i2cRxBuffer), 100);
	if(strlen(i2cRxBuffer) > 0)
	{
		if(i2cRxBuffer[0] == 'b' && i2cRxBuffer[1] == 't' && i2cRxBuffer[2] == 'n')
		{
			if(i2cRxBuffer[3] == '1') subKeyProcess(0);
			if(i2cRxBuffer[3] == '2') subKeyProcess(1);
			if(i2cRxBuffer[3] == '3') subKeyProcess(2);
		}

		else if(i2cRxBuffer[0] == 'r' && i2cRxBuffer[1] == 'g' && i2cRxBuffer[2] == 'b')
		{
			int temp_red, temp_green, temp_blue = 0;
			sscanf(i2cRxBuffer, "rgb-%d-%d-%d", &temp_red, &temp_green, &temp_blue);
			red = (uint8_t)temp_red;
			green = (uint8_t)temp_green;
			blue = (uint8_t)temp_blue;
			rgb_color(red, green, blue);
			red = 0;
			green = 0;
			blue = 0;
		}
	}
	memset(i2cRxBuffer, 0, sizeof(i2cRxBuffer));
}
