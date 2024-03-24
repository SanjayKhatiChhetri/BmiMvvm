package com.example.bmimvvm

import BMIViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmimvvm.ui.theme.BmiMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}

val Purple = Color(0xFF6200EE)
val PurpleAlpha = Purple.copy(alpha = 0.5f)

@Composable
fun BMICalculator(bmiViewModel: BMIViewModel = viewModel()) {
    Column(modifier = Modifier.padding(24.dp)) {
        // Height input
        OutlinedTextField(
            value = bmiViewModel.heightState.value,
            onValueChange = { newHeight ->
                // Update the ViewModel with the new height and recalculate BMI
                bmiViewModel.updateHeight(newHeight)
            },
            label = { Text("Height (m)") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple,
                unfocusedBorderColor = PurpleAlpha
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weight input
        OutlinedTextField(
            value = bmiViewModel.weightState.value,
            onValueChange = { newWeight ->
                // Update the ViewModel with the new weight and recalculate BMI
                bmiViewModel.updateWeight(newWeight)
            },
            label = { Text("Weight (kg)") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple,
                unfocusedBorderColor = PurpleAlpha
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the BMI if computed
        bmiViewModel.bmiState.value?.let { bmi ->
            Text(text = "Body mass index is %.2f".format(bmi),
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BmiMVVMTheme {
        BMICalculator()
    }
}




