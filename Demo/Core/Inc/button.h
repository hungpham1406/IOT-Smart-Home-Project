/*
 * button.h
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#ifndef INC_BUTTON_H_
#define INC_BUTTON_H_

#include "main.h"

#define NORMAL_STATE GPIO_PIN_SET
#define PRESSED_STATE GPIO_PIN_RESET

int isButtonPressed(int key_index);
void initStateForButton();
void getKeyInput();

#endif /* INC_BUTTON_H_ */
