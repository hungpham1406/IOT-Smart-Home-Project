/*
 * DHT11.h
 *
 *  Created on: Jun 9, 2024
 *      Author: Asus
 */

#ifndef INC_DHT11_H_
#define INC_DHT11_H_

#include "main.h"
#include "i2c-lcd.h"
#include "string.h"
#include "stdio.h"

#define DHT11_PORT GPIOA
#define DHT11_PIN GPIO_PIN_2


extern uint8_t RHI, RHD, TCI, TCD, SUM;
extern float tCelsius;
extern float tFahrenheit;
extern float RH;

void display_Temp(void);
void display_Humid(void);
void microDelay (uint16_t delay);
void activate_DHT11(void);
uint8_t DHT11_Start (void);
uint8_t DHT11_Read (void);

extern uint32_t pMillis, cMillis;

#endif /* INC_DHT11_H_ */
