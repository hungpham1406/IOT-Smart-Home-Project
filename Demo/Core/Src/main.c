/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * Copyright (c) 2024 STMicroelectronics.
  * All rights reserved.
  *
  * This software is licensed under terms that can be found in the LICENSE file
  * in the root directory of this software component.
  * If no LICENSE file comes with this software, it is provided AS-IS.
  *
  ******************************************************************************
  */
/* USER CODE END Header */
/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "stdio.h"
#include "i2c-lcd.h"
#include "button.h"
#include "DS3231.h"
#include "DHT11.h"
#include "automatic_mode.h"
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
ADC_HandleTypeDef hadc1;

I2C_HandleTypeDef hi2c1;

TIM_HandleTypeDef htim2;
TIM_HandleTypeDef htim3;

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_I2C1_Init(void);
static void MX_TIM2_Init(void);
static void MX_ADC1_Init(void);
static void MX_TIM3_Init(void);
/* USER CODE BEGIN PFP */

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/************* Variables from @ main.h ****************/
int state = 0;

/********************************DHT11 VARIABLES AND FUNCTION**********************************/
//uint8_t RHI, RHD, TCI, TCD, SUM;
//float tCelsius = 0;
//float tFahrenheit = 0;
//float RH = 0;
//
//void display_Temp(float Temp) {
//	char str[30];
//	lcd_put_cur(0, 0);
//	lcd_send_string("Temp: ");
//	sprintf(str, "%.1f", Temp);
//	lcd_send_string(str);
//}
//
//void display_Humid(float RH) {
//	char str[30];
//	lcd_put_cur(1, 0);
//	lcd_send_string("Humidity: ");
//	sprintf(str, "%.1f", RH);
//	lcd_send_string(str);
//}

//#define DHT11_PORT GPIOA
//#define DHT11_PIN GPIO_PIN_2
//
//void microDelay (uint16_t delay);
//uint8_t DHT11_Start (void);
//uint8_t DHT11_Read (void);
//
//uint32_t pMillis, cMillis;
//
//void microDelay (uint16_t delay)
//{
//  __HAL_TIM_SET_COUNTER(&htim2, 0);
//  while (__HAL_TIM_GET_COUNTER(&htim2) < delay);
//}

//uint8_t DHT11_Start (void)
//{
//  uint8_t Response = 0;
//  GPIO_InitTypeDef GPIO_InitStructPrivate = {0};
//  GPIO_InitStructPrivate.Pin = DHT11_PIN;
//  GPIO_InitStructPrivate.Mode = GPIO_MODE_OUTPUT_PP;
//  GPIO_InitStructPrivate.Speed = GPIO_SPEED_FREQ_LOW;
//  GPIO_InitStructPrivate.Pull = GPIO_NOPULL;
//  HAL_GPIO_Init(DHT11_PORT, &GPIO_InitStructPrivate); // set the pin as output
//  HAL_GPIO_WritePin (DHT11_PORT, DHT11_PIN, 0);   // pull the pin low
//  HAL_Delay(20);   // wait for 20ms
//  HAL_GPIO_WritePin (DHT11_PORT, DHT11_PIN, 1);   // pull the pin high
//  microDelay (30);   // wait for 30us
//  GPIO_InitStructPrivate.Mode = GPIO_MODE_INPUT;
//  GPIO_InitStructPrivate.Pull = GPIO_PULLUP;
//  HAL_GPIO_Init(DHT11_PORT, &GPIO_InitStructPrivate); // set the pin as input
//  microDelay (40);
//  if (!(HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN)))
//  {
//    microDelay (80);
//    if ((HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN))) Response = 1;
//  }
//  pMillis = HAL_GetTick();
//  cMillis = HAL_GetTick();
//  while ((HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN)) && pMillis + 2 > cMillis)
//  {
//    cMillis = HAL_GetTick();
//  }
//  return Response;
//}
//
//uint8_t DHT11_Read (void)
//{
//  uint8_t a,b;
//  for (a=0;a<8;a++)
//  {
//    pMillis = HAL_GetTick();
//    cMillis = HAL_GetTick();
//    while (!(HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN)) && pMillis + 2 > cMillis)
//    {  // wait for the pin to go high
//      cMillis = HAL_GetTick();
//    }
//    microDelay (40);   // wait for 40 us
//    if (!(HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN)))   // if the pin is low
//      b&= ~(1<<(7-a));
//    else
//      b|= (1<<(7-a));
//    pMillis = HAL_GetTick();
//    cMillis = HAL_GetTick();
//    while ((HAL_GPIO_ReadPin (DHT11_PORT, DHT11_PIN)) && pMillis + 2 > cMillis)
//    {  // wait for the pin to go low
//      cMillis = HAL_GetTick();
//    }
//  }
//  return b;
//}

/********************************LIGHT SENSOR VARIABLES**********************************/
uint16_t readValue;

void display_Light(uint16_t val) {
	char str[30];
	lcd_put_cur(0, 0);
//	lcd_send_string("Light: ");
	sprintf(str, "Light: %hu", val);
	lcd_send_string(str);
}

/********************************DS3231**********************************/
//#define DS3231_ADDRESS 0xD0
//
//uint8_t dec_To_Bcd(int val);
//int bcd_To_Dec(uint8_t val);
//
//void set_Time(uint8_t sec, uint8_t min, uint8_t hour, uint8_t dow, uint8_t dom,
//			  uint8_t month, uint8_t year);
//void get_Time(void);
//
//typedef struct {
//	uint8_t second;
//	uint8_t minute;
//	uint8_t hour;
//	uint8_t dayOfWeek;
//	uint8_t dayOfMonth;
//	uint8_t month;
//	uint8_t year;
//} TIME;
//
//TIME time;
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
/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_I2C1_Init();
  MX_TIM2_Init();
  MX_ADC1_Init();
  MX_TIM3_Init();
  /* USER CODE BEGIN 2 */
  HAL_TIM_Base_Start(&htim2);
  HAL_TIM_Base_Start_IT(&htim2);
  HAL_TIM_Base_Start_IT(&htim3);

  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
//  lcd_init();
//
//  lcd_send_string("Hello world!");
//  HAL_Delay(2000);
//
//  lcd_clear();
//  HAL_Delay(1000);

//  set_Time(0, 36, 21, 6, 22, 6, 24);
//  char time_buffer[30];

  state = INIT;
  while (1)
  {
//	  display_Temp(tCelsius);
//	  display_Humid(RH);
//
//	  if(DHT11_Start()) {
//		  RHI = DHT11_Read(); // Relative humidity integral
//		  RHD = DHT11_Read(); // Relative humidity decimal
//		  TCI = DHT11_Read(); // Celsius integral
//		  TCD = DHT11_Read(); // Celsius decimal
//		  SUM = DHT11_Read(); // Check sum
//
//		  if(SUM == RHI + RHD + TCI + TCD) {
//			  // Can use RHI and TCI for any purposes if whole number only needed
//			  tCelsius = (float)TCI + (float)(TCD/10.0);
//			  tFahrenheit = tCelsius * 9/5 + 32;
//			  RH = (float)RHI + (float)(RHD/10.0);
//			  // Can use tCelsius, tFahrenheit and RH for any purposes
//		  }
//	  }

//	  HAL_ADC_Start(&hadc1);
//	  HAL_ADC_PollForConversion(&hadc1, 500);
//	  readValue = HAL_ADC_GetValue(&hadc1);
//	  display_Light(readValue);
//	  HAL_ADC_Stop(&hadc1);

//	  get_Time();
//	  sprintf(time_buffer, "%02d:%02d:%02d", time_def.hour, time_def.minute, time_def.second);
//	  lcd_put_cur(0, 0);
//	  lcd_send_string(time_buffer);
//
//	  sprintf(time_buffer, "%02d-%02d-20%02d", time_def.dayOfMonth, time_def.month, time_def.year);
//	  lcd_put_cur(1, 0);
//	  lcd_send_string(time_buffer);

//	  HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_13);

	  automatic_run();

	  HAL_Delay(500);

    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Configure the main internal regulator output voltage
  */
  __HAL_RCC_PWR_CLK_ENABLE();
  __HAL_PWR_VOLTAGESCALING_CONFIG(PWR_REGULATOR_VOLTAGE_SCALE1);

  /** Initializes the RCC Oscillators according to the specified parameters
  * in the RCC_OscInitTypeDef structure.
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSE;
  RCC_OscInitStruct.HSEState = RCC_HSE_ON;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_ON;
  RCC_OscInitStruct.PLL.PLLSource = RCC_PLLSOURCE_HSE;
  RCC_OscInitStruct.PLL.PLLM = 4;
  RCC_OscInitStruct.PLL.PLLN = 80;
  RCC_OscInitStruct.PLL.PLLP = RCC_PLLP_DIV2;
  RCC_OscInitStruct.PLL.PLLQ = 4;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }

  /** Initializes the CPU, AHB and APB buses clocks
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_PLLCLK;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV2;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_1) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief ADC1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_ADC1_Init(void)
{

  /* USER CODE BEGIN ADC1_Init 0 */

  /* USER CODE END ADC1_Init 0 */

  ADC_ChannelConfTypeDef sConfig = {0};

  /* USER CODE BEGIN ADC1_Init 1 */

  /* USER CODE END ADC1_Init 1 */

  /** Configure the global features of the ADC (Clock, Resolution, Data Alignment and number of conversion)
  */
  hadc1.Instance = ADC1;
  hadc1.Init.ClockPrescaler = ADC_CLOCK_SYNC_PCLK_DIV2;
  hadc1.Init.Resolution = ADC_RESOLUTION_12B;
  hadc1.Init.ScanConvMode = DISABLE;
  hadc1.Init.ContinuousConvMode = ENABLE;
  hadc1.Init.DiscontinuousConvMode = DISABLE;
  hadc1.Init.ExternalTrigConvEdge = ADC_EXTERNALTRIGCONVEDGE_NONE;
  hadc1.Init.ExternalTrigConv = ADC_SOFTWARE_START;
  hadc1.Init.DataAlign = ADC_DATAALIGN_RIGHT;
  hadc1.Init.NbrOfConversion = 1;
  hadc1.Init.DMAContinuousRequests = DISABLE;
  hadc1.Init.EOCSelection = ADC_EOC_SINGLE_CONV;
  if (HAL_ADC_Init(&hadc1) != HAL_OK)
  {
    Error_Handler();
  }

  /** Configure for the selected ADC regular channel its corresponding rank in the sequencer and its sample time.
  */
  sConfig.Channel = ADC_CHANNEL_9;
  sConfig.Rank = 1;
  sConfig.SamplingTime = ADC_SAMPLETIME_15CYCLES;
  if (HAL_ADC_ConfigChannel(&hadc1, &sConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN ADC1_Init 2 */

  /* USER CODE END ADC1_Init 2 */

}

/**
  * @brief I2C1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_I2C1_Init(void)
{

  /* USER CODE BEGIN I2C1_Init 0 */

  /* USER CODE END I2C1_Init 0 */

  /* USER CODE BEGIN I2C1_Init 1 */

  /* USER CODE END I2C1_Init 1 */
  hi2c1.Instance = I2C1;
  hi2c1.Init.ClockSpeed = 100000;
  hi2c1.Init.DutyCycle = I2C_DUTYCYCLE_2;
  hi2c1.Init.OwnAddress1 = 0;
  hi2c1.Init.AddressingMode = I2C_ADDRESSINGMODE_7BIT;
  hi2c1.Init.DualAddressMode = I2C_DUALADDRESS_DISABLE;
  hi2c1.Init.OwnAddress2 = 0;
  hi2c1.Init.GeneralCallMode = I2C_GENERALCALL_DISABLE;
  hi2c1.Init.NoStretchMode = I2C_NOSTRETCH_DISABLE;
  if (HAL_I2C_Init(&hi2c1) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN I2C1_Init 2 */

  /* USER CODE END I2C1_Init 2 */

}

/**
  * @brief TIM2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM2_Init(void)
{

  /* USER CODE BEGIN TIM2_Init 0 */

  /* USER CODE END TIM2_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};

  /* USER CODE BEGIN TIM2_Init 1 */

  /* USER CODE END TIM2_Init 1 */
  htim2.Instance = TIM2;
  htim2.Init.Prescaler = 40-1;
  htim2.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim2.Init.Period = 0xffff-1;
  htim2.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim2.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_Base_Init(&htim2) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim2, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim2, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM2_Init 2 */

  /* USER CODE END TIM2_Init 2 */

}

/**
  * @brief TIM3 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM3_Init(void)
{

  /* USER CODE BEGIN TIM3_Init 0 */

  /* USER CODE END TIM3_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};

  /* USER CODE BEGIN TIM3_Init 1 */

  /* USER CODE END TIM3_Init 1 */
  htim3.Instance = TIM3;
  htim3.Init.Prescaler = 40-1;
  htim3.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim3.Init.Period = 9999;
  htim3.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim3.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_Base_Init(&htim3) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim3, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim3, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM3_Init 2 */

  /* USER CODE END TIM3_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};
/* USER CODE BEGIN MX_GPIO_Init_1 */
/* USER CODE END MX_GPIO_Init_1 */

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOH_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();
  __HAL_RCC_GPIOD_CLK_ENABLE();
  __HAL_RCC_GPIOA_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);

  /*Configure GPIO pin : PD13 */
  GPIO_InitStruct.Pin = GPIO_PIN_13;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOD, &GPIO_InitStruct);

  /*Configure GPIO pins : button1_Pin button2_Pin button3_Pin */
  GPIO_InitStruct.Pin = button1_Pin|button2_Pin|button3_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

/* USER CODE BEGIN MX_GPIO_Init_2 */
/* USER CODE END MX_GPIO_Init_2 */
}

/* USER CODE BEGIN 4 */
void HAL_TIM_PeriodElapsedCallback(TIM_HandleTypeDef *htim) {
	timerRun();
	getKeyInput();
}
/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */
  __disable_irq();
  while (1)
  {
  }
  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     ex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */
