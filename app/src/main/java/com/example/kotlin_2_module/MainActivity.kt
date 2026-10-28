package com.example.kotlin_2_module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_2_module.ui.theme.Kotlin_2_moduleTheme
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kotlin_2_moduleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Hello (name = null)
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?) {
    Box {
        if (name == null) {
            Text (text = "Имя не задано")
        } else {
            Text (text = "Привет, $name!")
        }
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,orientation=portrait"
)
@Composable
fun HelloPreviewPortrait() {
    Kotlin_2_moduleTheme {
        Hello(name = "Дмитрий")
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=640dp,height=360dp,orientation=landscape"
)
@Composable
fun HelloPreviewLandscape() {
    Kotlin_2_moduleTheme {
        Hello(name = "Дмитрий")
    }
}


@Preview(
    showSystemUi = true,
    device = "spec:width=200dp,height=200dp,shape=Round"
)
@Composable
fun HelloPreviewRound() {
    Kotlin_2_moduleTheme {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Hello(name = "Дмитрий")
        }
    }
}
