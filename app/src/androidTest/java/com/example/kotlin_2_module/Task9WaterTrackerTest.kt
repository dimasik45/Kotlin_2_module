package com.example.kotlin_2_module // Проверьте, чтобы пакет совпадал с вашим проектом

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class Task9WaterTrackerTest {

    // Правило для запуска и тестирования Compose-компонентов
    @get:Rule
    val composeTestRule = createComposeRule()

    // 1) Проверить наличие и корректную работу кнопки добавления жидкости
    @Test
    fun checkAddWaterButton_existsAndIncrementsWater() {
        composeTestRule.setContent {
            WaterTracker()
        }

        // Проверяем, что изначально воды 0 мл и кнопка "+250 мл" отображается на экране
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()

        // Нажимаем на кнопку "+250 мл" 1 раз и проверяем, что стало 250 мл
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("250 мл").assertIsDisplayed()

        // Нажимаем еще 2 раза (всего 3 раза) и проверяем, что стало 750 мл
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("750 мл").assertIsDisplayed()
    }

    // 2) Проверить наличие и корректную работу кнопки "Завершить день" (сброс воды)
    @Test
    fun checkFinishDayButton_existsAndResetsWaterCount() {
        composeTestRule.setContent {
            WaterTracker()
        }

        // Проверяем наличие кнопки "Завершить день" на экране
        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed()

        // Добавляем немного воды (500 мл)
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("500 мл").assertIsDisplayed()

        // Нажимаем "Завершить день" и проверяем, что счетчик воды сбросился обратно в "0 мл"
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    // 3) Проверить, что выводится необходимый текст со значением количества дней подряд (>= 1500 мл)
    @Test
    fun checkDaysStreakText_updatesCorrectlyOn1500mlAndResetsWhenLess() {
        composeTestRule.setContent {
            WaterTracker()
        }

        // Проверяем начальный вывод текста с количеством дней
        composeTestRule.onNodeWithText("Дней подряд: 0").assertIsDisplayed()

        // День 1: выпиваем 1500 мл (6 нажатий по 250 мл) и завершаем день
        repeat(6) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
        composeTestRule.onNodeWithText("1500 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("Завершить день").performClick()

        // Проверяем, что счетчик дней увеличился до 1, а вода сбросилась
        composeTestRule.onNodeWithText("Дней подряд: 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()

        // День 2: выпиваем меньше нормы (только 250 мл) и завершаем день
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("Завершить день").performClick()

        // Проверяем, что счетчик дней сбросился до 0
        composeTestRule.onNodeWithText("Дней подряд: 0").assertIsDisplayed()
    }
}
