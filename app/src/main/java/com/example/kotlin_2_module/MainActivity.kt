package com.example.kotlin_2_module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// 1-й вариант из задания: Синий прямоугольник с растянутым фиолетовым овалом
@Composable
fun Task6Variant1() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle), // картинка из ресурсов
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Растягивает круг в овал
            colorFilter = ColorFilter.tint(Color(0xFF802A76)),
            modifier = Modifier.fillMaxSize()
        )
    }
}


// 2-й вариант из задания: Черный прямоугольник с кругом в правом верхнем углу
@Composable
fun Task6Variant2() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black)
            .padding(8.dp)                  // Отступ внутри черного блока
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()                 // Не растягиваем на весь экран
                .size(40.dp)                       // Задаем равные стороны для ровного круга
                .align(Alignment.TopEnd)           // Прижимаем в правый верхний угол
                .clip(CircleShape)                 // Делаем форму круга
                .background(Color(0xFFFF5722))     // Оранжево-красный цвет
        )
    }
}

// Общий предпросмотр сразу для обоих вариантов задания 6
@Preview(showBackground = true, name = "Task 6 Preview")
@Composable
fun Task6Preview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Task6Variant1()
        Task6Variant2()
    }
}
