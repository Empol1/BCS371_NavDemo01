package edu.farmingdale.bcs371_w7_demo_nav

import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("second_screen") {
            SecondScreen(navController)
        }
        composable("pizza_screen") {
            PizzaPartyScreen(navController)
        }
        composable("gpa_screen") {
            gpaappFun(navController)
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp).wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "First Screen")

            Button(onClick = { navController.navigate("second_screen") }) {
                Text(text = "Go to Second Screen")
            }

            Button(onClick = { navController.navigate("pizza_screen") }) {
                Text(text = "Go to Pizza Party")
            }

            Button(onClick = { navController.navigate("gpa_screen") }) { // Navigation to GPA Calculator
                Text(text = "Go to GPA Calculator")
            }
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var isSwitchChecked by remember { mutableStateOf(true) } // Switch state

    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderValue,
            onValueChange = { if (isSwitchChecked) sliderValue = it }, // disables if switch is on off
            modifier = Modifier.fillMaxWidth()
        )

        Text(fontSize = 20.sp, text = "Second Screen")

        Button(onClick = { context.startActivity(Intent(context, MainActivity2::class.java)) }) {
            Text(fontSize = 20.sp, text = "Go to other Activity")
        }

        // Checkbox to enable/disable the slider
        Checkbox(
            checked = isSwitchChecked,
            onCheckedChange = { isSwitchChecked = it },
            modifier = Modifier.padding(10.dp)
        )
        Text(text = "Enable Slider", modifier = Modifier.padding(start = 8.dp)) // label the checkbox
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1000, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(3000)
        navController.navigate("first_screen")
    }

    Box(contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.fsclogo), contentDescription = "")
    }
}