/*
 * button.h
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#ifndef INC_BUTTON_H_
#define INC_BUTTON_H_

#define NUMBER_BUTTONS		3

#include "main.h"
#include "string.h"
#include "stdio.h"

#define NORMAL_STATE GPIO_PIN_SET
#define PRESSED_STATE GPIO_PIN_RESET

int isButtonPressed(int key_index);
void initStateForButton();
void getKeyInput();
void getKeyFromAdafruit();

#endif /* INC_BUTTON_H_ */
