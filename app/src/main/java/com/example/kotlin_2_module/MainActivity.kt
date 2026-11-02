package com.example.kotlin_2_module // Проверьте совпадение пакета с MainActivity.kt

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

// Классы данных из условия задания
data class Cart(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

@Composable
fun ShoppingCartScreen() {
    val context = LocalContext.current

    // Состояние корзины со стартовым списком товаров (или пустым списком)
    var cart by remember {
        mutableStateOf(
            Cart(
                products = listOf(
                    Product(id = 0, name = "Товар #1", price = 100),
                    Product(id = 1, name = "Товар #2", price = 150),
                    Product(id = 2, name = "Товар #3", price = 200)
                )
            )
        )
    }

    // Правильный подсчет суммы и количества без дублирования при рекомпозиции
    val totalSum = cart.products.sumOf { it.price }
    val productSize = cart.products.size

    // 2. Если сумма товаров больше 500 — показываем Toast «Доставка бесплатная!»
    // LaunchedEffect гарантирует, что Toast покажется только при превышении порога 500,
    // а не при каждой перерисовке экрана
    val isFreeDelivery = totalSum > 500
    LaunchedEffect(isFreeDelivery) {
        if (isFreeDelivery) {
            Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Вывод списка товаров в корзине
        for (product in cart.products) {
            Text(
                text = "${product.name} - ${product.price} руб.",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Итоговая информация о корзине
        Text(
            text = "Товаров на сумму: $totalSum руб. ($productSize шт.)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 1. Кнопка «Добавить товар»: добавляет новый товар в корзину
        Button(
            onClick = {
                val newId = cart.products.size
                val newProduct = Product(
                    id = newId,
                    name = "Товар #${newId + 1}",
                    price = Random.nextInt(100, 300) // Или фиксированная цена, например 200
                )
                cart = cart.copy(products = cart.products + newProduct)
            }
        ) {
            Text(text = "Добавить товар")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 3. Кнопка «Удалить товар» показывается ТОЛЬКО при наличии товаров в корзине
        if (cart.products.isNotEmpty()) {
            Button(
                onClick = {
                    // 4. При нажатии удаляется последний товар из списка
                    cart = cart.copy(products = cart.products.dropLast(1))
                }
            ) {
                Text(text = "Удалить товар")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingCartScreenPreview() {
    ShoppingCartScreen()
}
