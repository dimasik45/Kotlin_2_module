package com.example.kotlin_2_module // Проверьте, чтобы имя пакета совпадало с вашим проектом

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Модель данных для хранения информации о контакте
data class Contact(
    val firstName: String,
    val patronymic: String,
    val lastName: String,
    val phone: String,
    val address: String
)

// Тестовые данные задания
val sampleContacts = listOf(
    Contact(
        firstName = "Евгений",
        patronymic = "Андреевич",
        lastName = "Лукашин",
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12"
    ),
    Contact(
        firstName = "Василий",
        patronymic = "Егорович",
        lastName = "Кузякин",
        phone = "—",
        address = "Ивановская область, дер. Крутово, д. 4"
    ),
    Contact(
        firstName = "Людмила",
        patronymic = "Прокофьевна",
        lastName = "Калугина",
        phone = "+7 495 788 78 78",
        address = "Москва, Большая Никитская, д. 43, кв. 290"
    )
)

// 1. Первый скриншот: Одиночный контакт с использованием Column (выравнивание слева)
@Composable
fun ContactColumnSimple(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Имя: ${contact.firstName}", fontSize = 14.sp)
        Text(text = "Отчество: ${contact.patronymic}", fontSize = 14.sp)
        Text(text = "Фамилия: ${contact.lastName}", fontSize = 14.sp)
        Text(text = "Мобильный телефон: ${contact.phone}", fontSize = 14.sp)
        Text(text = "Адрес: ${contact.address}", fontSize = 14.sp)
    }
}


// 2. Второй скриншот: Список карточек контактов на сером фоне (Column + центрирование)
@Composable
fun ContactCardColumnItem(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray) // Серый фон карточки
            .padding(12.dp),             // Внутренний отступ карточки
        horizontalAlignment = Alignment.CenterHorizontally // Выравнивание строк по центру
    ) {
        Text(text = "Имя: ${contact.firstName}", fontSize = 14.sp, textAlign = TextAlign.Center)
        Text(text = "Отчество: ${contact.patronymic}", fontSize = 14.sp, textAlign = TextAlign.Center)
        Text(text = "Фамилия: ${contact.lastName}", fontSize = 14.sp, textAlign = TextAlign.Center)
        Text(text = "Мобильный телефон: ${contact.phone}", fontSize = 14.sp, textAlign = TextAlign.Center)
        Text(text = "Адрес: ${contact.address}", fontSize = 14.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun ContactListColumn(contacts: List<Contact>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между серыми блоками
    ) {
        for (contact in contacts) {
            ContactCardColumnItem(contact = contact)
        }
    }
}


// 3. Третий скриншот: Карточка контакта через контейнер Row (текст + иконка справа)
@Composable
fun ContactRowCard(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically // Центрируем звезду по вертикали относительно текста
    ) {
        // Левая часть: Колонка с текстом (weight(1f) занимает всё свободное место слева от иконки)
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Имя: ${contact.firstName}", fontSize = 14.sp, textAlign = TextAlign.Center)
            Text(text = "Отчество: ${contact.patronymic}", fontSize = 14.sp, textAlign = TextAlign.Center)
            Text(text = "Фамилия: ${contact.lastName}", fontSize = 14.sp, textAlign = TextAlign.Center)
            Text(text = "Мобильный телефон: ${contact.phone}", fontSize = 14.sp, textAlign = TextAlign.Center)
            Text(text = "Адрес: ${contact.address}", fontSize = 14.sp, textAlign = TextAlign.Center)
        }

        // Правая часть: встроенная звезда Android
        Image(
            painter = painterResource(id = android.R.drawable.btn_star_big_on),
            contentDescription = "Избранный контакт",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "1. ContactColumnPreview")
@Composable
fun ContactColumnPreview() {
    Column(modifier = Modifier.padding(top = 48.dp)) {
        ContactColumnSimple(contact = sampleContacts[0])
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "2. ListPreview")
@Composable
fun ListPreview() {
    Column(modifier = Modifier.padding(top = 48.dp)) {
        ContactListColumn(contacts = sampleContacts)
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "3. ContactRowPreview")
@Composable
fun ContactRowPreview() {
    Column(modifier = Modifier.padding(top = 48.dp)) {
        ContactRowCard(contact = sampleContacts[0])
    }
}
