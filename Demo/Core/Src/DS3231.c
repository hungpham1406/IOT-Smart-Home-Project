/*
 * DS3231.c
 *
 *  Created on: Apr 5, 2024
 *      Author: Asus
 */

#include "DS3231.h"

extern I2C_HandleTypeDef hi2c1;
char time_buffer[30];

TIME time_def;

uint8_t dec_To_Bcd(int val) {
	return (uint8_t)((val/10*16) + (val%10));
}

int bcd_To_Dec(uint8_t val) {
	return (int)((val/16*10) + (val%16));
}

void set_Time(uint8_t sec, uint8_t min, uint8_t hour, uint8_t dow, uint8_t dom,
			  uint8_t month, uint8_t year) {
	uint8_t set_time_buffer[7];
	set_time_buffer[0] = dec_To_Bcd(sec);
	set_time_buffer[1] = dec_To_Bcd(min);
	set_time_buffer[2] = dec_To_Bcd(hour);
	set_time_buffer[3] = dec_To_Bcd(dow);
	set_time_buffer[4] = dec_To_Bcd(dom);
	set_time_buffer[5] = dec_To_Bcd(month);
	set_time_buffer[6] = dec_To_Bcd(year);

	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x00, 1, set_time_buffer, 7, 1000);
}

void get_Time(void) {
	uint8_t get_time_buffer[7];
	HAL_I2C_Mem_Read(&hi2c1, DS3231_ADDRESS, 0x00, 1, get_time_buffer, 7, 1000);

	time_def.second 	= bcd_To_Dec(get_time_buffer[0]);
	time_def.minute 	= bcd_To_Dec(get_time_buffer[1]);
	time_def.hour 		= bcd_To_Dec(get_time_buffer[2]);
	time_def.dayOfWeek 	= bcd_To_Dec(get_time_buffer[3]);
	time_def.dayOfMonth = bcd_To_Dec(get_time_buffer[4]);
	time_def.month 		= bcd_To_Dec(get_time_buffer[5]);
	time_def.year 		= bcd_To_Dec(get_time_buffer[6]);
}

void set_Second(uint8_t second) {
	uint8_t second_Temp = dec_To_Bcd(second);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x00, 1, &second_Temp, 1, 1000);
}

void set_Minute(uint8_t minute) {
	uint8_t minute_Temp = dec_To_Bcd(minute);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x01, 1, &minute_Temp, 1, 1000);
}

void set_Hour(uint8_t hour) {
	uint8_t hour_Temp = dec_To_Bcd(hour);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x02, 1, &hour_Temp, 1, 1000);
}

void set_DayOfMonth(uint8_t dayOfMonth) {
	uint8_t dayOfMonth_Temp = dec_To_Bcd(dayOfMonth);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x04, 1, &dayOfMonth_Temp, 1, 1000);
}

void set_Month(uint8_t month) {
	uint8_t month_Temp = dec_To_Bcd(month);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x05, 1, &month_Temp, 1, 1000);
}

void set_Year(uint8_t year) {
	uint8_t year_Temp = dec_To_Bcd(year);
	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x05, 1, &year_Temp, 1, 1000);
}

void display_Time(void) {
	get_Time();
	sprintf(time_buffer, "%02d:%02d", time_def.hour, time_def.minute);
	lcd_put_cur(1, 0);
	lcd_send_string(time_buffer);

	sprintf(time_buffer, "%02d-%02d-20%02d", time_def.dayOfMonth, time_def.month, time_def.year);
	lcd_put_cur(1, 6);
	lcd_send_string(time_buffer);
}
