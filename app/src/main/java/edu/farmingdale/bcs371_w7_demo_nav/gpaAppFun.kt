package edu.farmingdale.bcs371_w7_demo_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun gpaappFun(navController: NavController) {
    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }

    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Compute GPA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            label = { Text("Course 1 Grade") },
        )
        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            label = { Text("Course 2 Grade") },
        )
        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            label = { Text("Course 3 Grade") },
        )

        Button(onClick = {
            if (btnLabel == "Compute GPA") {
                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null) {
                    gpa = gpaVal.toString()

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                    btnLabel = "Clear"
                } else {
                    gpa = "Invalid input"
                }
            } else {

                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
                btnLabel = "Compute GPA"
            }
        }) {
            Text(btnLabel)
        }

        if (gpa.isNotEmpty()) {
            Text(text = "GPA: $gpa", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

fun calGPA(grade1: String, grade2: String, grade3: String): Double? {
    return try {
        val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
        grades.average()
    } catch (e: Exception) {
        null
    }
}
