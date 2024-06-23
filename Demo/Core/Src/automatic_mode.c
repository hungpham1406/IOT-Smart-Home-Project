/*
 * automatic_mode.c
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#include "automatic_mode.h"

void display_Adjustment(int state, char* name_Adjust_Var, uint8_t adjust_Var) {
	char str[16];

	lcd_clear();
	lcd_put_cur(0, 0);
	sprintf(str, "STATE: %d", state);
	lcd_send_string(str);

	str[0] = '\0';
	lcd_put_cur(1, 0);
	lcd_send_string(str);
	sprintf(str, "%s: %d", name_Adjust_Var, adjust_Var);
	lcd_send_string(str);
}

uint8_t temp = 0;

void automatic_run() {
	switch(state) {
	case INIT:
		lcd_init();
		initStateForButton();

		lcd_send_string("Hello world!");
		HAL_Delay(2000);

		lcd_clear();
		state = STATE1;
		setTimer1(100);
		setTimer2(100);
		break;

	case STATE1:
		if(timer1_flag == 1) {
			setTimer1(500);

			display_Humid();
			display_Temp();
			display_Time();
			activate_DHT11();
		}

		if(timer2_flag == 1) {
			setTimer2(100);
//			HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);
		}

		if(isButtonPressed(0)) {
			state = STATE2;
			setTimer2(100);
			lcd_clear();
			temp = time_def.hour;
			lcd_clear();
		}
		break;

	case STATE2:									//Adjust hour
		display_Adjustment(STATE2, "Hour", temp);

		if(timer2_flag == 1) {
			setTimer2(100);
			HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);
		}

		if(isButtonPressed(1)) {
			temp += 1;
			if(temp > 23) temp = 0;
			display_Adjustment(STATE2, "Hour", temp);
		}

		if(isButtonPressed(2)) {
			set_Hour(temp);
			set_Second(0);
			state = STATE1;
		}

		if(isButtonPressed(0)) {
			lcd_clear();
			state = STATE1;
			setTimer1(100);
			setTimer2(100);
			temp = time_def.minute;
		}

		break;

	case STATE3:									//Adjust minute
		if(timer2_flag == 1) {
			setTimer2(100);
			HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);
		}

		if(isButtonPressed(1)) {
			temp += 1;
			if(temp > 60) temp = 0;
		}

		if(isButtonPressed(2)) {
			set_Minute(temp);
			set_Second(0);
			state = STATE1;
		}

		if(isButtonPressed(0)) {
			state = STATE4;
			setTimer1(100);
			setTimer2(100);
			temp = time_def.dayOfMonth;
		}

		break;

	case STATE4:									//Adjust day of month
		if(timer2_flag == 1) {
			setTimer2(100);
			HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);
		}

		if(isButtonPressed(1)) {
			temp += 1;
			uint8_t month = time_def.month;
			uint8_t year = time_def.year;
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(temp > 31) temp = 1;
			}
			else if(month == 4 || month == 6 || month == 9|| month == 11) {
				if(temp > 30) temp = 1;
			}
			else if(month == 2) {
				if(year % 4 == 0) {
					if(temp > 29) temp = 1;
				}
				else
					if(temp > 28) temp = 1;
			}
		}

		if(isButtonPressed(2)) {
			set_DayOfMonth(temp);
			state = STATE1;
		}

		if(isButtonPressed(0)) {
			state = STATE5;
			setTimer1(100);
			setTimer2(100);
			temp = time_def.month;
		}

	case STATE5:									//Adjust  month
		if(timer2_flag == 1) {
			setTimer2(100);
			HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);
		}

		if(isButtonPressed(1)) {
			temp += 1;
			if(temp >= 12) temp = 1;
		}

		if(isButtonPressed(2)) {
			set_Month(temp);
			state = STATE1;
		}

		if(isButtonPressed(0)) {
			state = STATE1;
			setTimer1(100);
			setTimer2(100);
		}

		break;
	default:
		break;
	}
}
