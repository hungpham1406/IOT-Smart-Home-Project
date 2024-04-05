/*
 * DS3231.c
 *
 *  Created on: Apr 5, 2024
 *      Author: Asus
 */

//#include "DS3231.h"
//
//extern I2C_HandleTypeDef hi2c1;
//
//uint8_t dec_To_Bcd(int val) {
//	return (uint8_t)((val/10*16) + (val%10));
//}
//
//int bcd_To_Dec(uint8_t val) {
//	return (int)((val/16*10) + (val%16));
//}
//
//void set_Time(uint8_t sec, uint8_t min, uint8_t hour, uint8_t dow, uint8_t dom,
//			  uint8_t month, uint8_t year) {
//	uint8_t set_time_buffer[7];
//	set_time_buffer[0] = dec_To_Bcd(sec);
//	set_time_buffer[1] = dec_To_Bcd(min);
//	set_time_buffer[2] = dec_To_Bcd(hour);
//	set_time_buffer[3] = dec_To_Bcd(dow);
//	set_time_buffer[4] = dec_To_Bcd(dom);
//	set_time_buffer[5] = dec_To_Bcd(month);
//	set_time_buffer[6] = dec_To_Bcd(year);
//
//	HAL_I2C_Mem_Write(&hi2c1, DS3231_ADDRESS, 0x00, 1, set_time_buffer, 7, 1000);
//}
//
//void get_Time(void) {
//	uint8_t get_time_buffer[7];
//	HAL_I2C_Mem_Read(&hi2c1, DS3231_ADDRESS, 0x00, 1, get_time_buffer, 7, 1000);
//
//	time.second 	= bcd_To_Dec(get_time_buffer[0]);
//	time.minute 	= bcd_To_Dec(get_time_buffer[1]);
//	time.hour 		= bcd_To_Dec(get_time_buffer[2]);
//	time.dayOfWeek 	= bcd_To_Dec(get_time_buffer[3]);
//	time.dayOfMonth = bcd_To_Dec(get_time_buffer[4]);
//	time.month 		= bcd_To_Dec(get_time_buffer[5]);
//	time.year 		= bcd_To_Dec(get_time_buffer[6]);
//}
