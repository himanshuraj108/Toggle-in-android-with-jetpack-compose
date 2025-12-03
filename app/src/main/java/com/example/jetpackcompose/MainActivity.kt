package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewSwitch()
        }
    }
}

//@Composable
//fun BasicSwitchExample() {
//    var isChecked by remember { mutableStateOf(false) }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text(text = if (isChecked) "ON" else "OFF",
//            color = if(isChecked) Color.Blue else Color.Red
//            )
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Switch(
//            checked = isChecked,
//            onCheckedChange = { isChecked = it },
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = if(isChecked) Color.Blue else Color.Red,
//                checkedTrackColor = Color(0xFFCB1616),
//                uncheckedThumbColor = Color.Red,
//                uncheckedTrackColor = if(isChecked) Color.Blue else Color.Red
//
//            )
//        )
//    }
//}
//
//
//@Composable
//fun SliderExample(){
//    var sliderValue by remember { mutableStateOf(0f) }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Value ${sliderValue.toInt()}")
//
//        Slider(
//            value = sliderValue,
//            onValueChange = { sliderValue = it },
//            valueRange = 0f..100f,
//            colors = SliderDefaults.colors(
//                thumbColor = Color.Red,
//                activeTrackColor = if(sliderValue < 50) Color.Red else Color.Blue,
//                inactiveTrackColor = Color.LightGray
//            )
//        )
//    }
//}

@Composable
fun RadioButtonExample() {
    var selectedOption by remember { mutableStateOf("Option 1") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Choose an option:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "Option 1",
                onClick = { selectedOption = "Option 1" }
            )
            Text("Option 1")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "Option 2",
                onClick = { selectedOption = "Option 2" }
            )
            Text("Option 2")
        }
    }
}

@Composable
fun RadioGroupExample(){
    val options = listOf("Male","Female","Others")
    var selectedOption by remember { mutableStateOf(options[0])}

    Column( modifier = Modifier.padding(16.dp)
    ) {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {selectedOption  = option}
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = "Your selected option is ${selectedOption}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSwitch() {
    RadioGroupExample()
}
