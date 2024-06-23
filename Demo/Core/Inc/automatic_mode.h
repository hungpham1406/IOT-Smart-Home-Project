/*
 * automatic_Mode.h
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#ifndef INC_AUTOMATIC_MODE_H_
#define INC_AUTOMATIC_MODE_H_

#include "main.h"
#include "button.h"
#include "i2c-lcd.h"
#include "DHT11.h"
#include "DS3231.h"
#include "software_timer.h"

void automatic_run();
void switch_state_2();
void display_Adjustment(int state, char* name_Adjust_Var, uint8_t adjust_Var);

#endif /* INC_AUTOMATIC_MODE_H_ */
