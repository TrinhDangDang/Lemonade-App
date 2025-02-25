package com.example.lemonade

import android.R.attr.shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1)}
    Scaffold(
        topBar = {
            // Material 3: CenterAlignedTopAppBar, SmallTopAppBar, or TopAppBar
            CenterAlignedTopAppBar(
                title = { Text("Lemonade") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFf9e44c)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,

            ) {
            when (currentStep) {
                (1) -> LemonTextAndImage(
                    painterResource(R.drawable.lemon_tree),
                    message = stringResource(R.string.lemon_tree),
                    step = currentStep,
                    onStepChange = { currentStep = it })

                (2) -> LemonTextAndImage(
                    painterResource(R.drawable.lemon_squeeze),
                    message = stringResource(R.string.lemon_squeeze),
                    step = currentStep,
                    onStepChange = { currentStep = it })

                (3) -> LemonTextAndImage(
                    painterResource(R.drawable.lemon_drink),
                    message = stringResource(R.string.lemon_drink),
                    step = currentStep,
                    onStepChange = { currentStep = it })

                (4) -> LemonTextAndImage(
                    painterResource(R.drawable.lemon_restart),
                    message = stringResource(R.string.lemon_restart),
                    step = currentStep,
                    onStepChange = { currentStep = it })
            }
        }
    }
}

@Composable
fun LemonTextAndImage(painter: Painter, message: String,step: Int, onStepChange: (Int) -> Unit) {
    var count by remember { mutableStateOf(0) }
    var squeezeCount by remember { mutableStateOf((2..4).random()) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
            when(step){
                1 -> onStepChange(2)
                2 -> {
                    count++
                    if(count == squeezeCount) {
                        count = 0
                        squeezeCount = (2..4).random()
                        onStepChange(3)
                    }
                }
                3 -> onStepChange(4)
                4 -> onStepChange(1)
            }
        },
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFc3ecd2))
        ) {
            Image(
                painter = painter,
                contentDescription = message,
            )
        }
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Text(
            text = message
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonApp()
    }
}