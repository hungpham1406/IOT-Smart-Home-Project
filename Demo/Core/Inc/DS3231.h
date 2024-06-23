/*
 * DS3231.h
 *
 *  Created on: Apr 5, 2024
 *      Author: Asus
 */

#ifndef INC_DS3231_H_
#define INC_DS3231_H_

#include "main.h"
#include "i2c-lcd.h"
#include "stdio.h"
//#include "sys/time.h"
//#include "time.h"

#define DS3231_ADDRESS 0xD0

uint8_t dec_To_Bcd(int val);
int bcd_To_Dec(uint8_t val);

void set_Time(uint8_t sec, uint8_t min, uint8_t hour, uint8_t dow, uint8_t dom,
			  uint8_t month, uint8_t year);
void get_Time(void);

void set_Second(uint8_t second);
void set_Hour(uint8_t hour);
void set_Minute(uint8_t minute);
void set_DayOfMonth(uint8_t dayOfMonth);
void set_Month(uint8_t month);
void set_Year(uint8_t year);

void display_Time(void);

typedef struct {
	uint8_t second;
	uint8_t minute;
	uint8_t hour;
	uint8_t dayOfWeek;
	uint8_t dayOfMonth;
	uint8_t month;
	uint8_t year;
} TIME;

extern TIME time_def;

#endif /* INC_DS3231_H_ */
