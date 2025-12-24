package com.example.jetpackcompose

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.net.Uri
import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlin.jvm.java


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtomNavScaffold()
        }
    }
}

@Composable
fun BasicSwitchExample() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = if (isChecked) "ON" else "OFF",
            color = if (isChecked) Color.Blue else Color.Red
        )
        Spacer(modifier = Modifier.width(8.dp))

        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = if (isChecked) Color.Blue else Color.Red,
                checkedTrackColor = Color(0xFFCB1616),
                uncheckedThumbColor = Color.Red,
                uncheckedTrackColor = if (isChecked) Color.Blue else Color.Red

            )
        )
    }
}


@Composable
fun SliderExample() {
    var sliderValue by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Value ${sliderValue.toInt()}")

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = if (sliderValue < 50) Color.Red else Color.Blue,
                inactiveTrackColor = Color.LightGray
            )
        )
    }
}

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
fun RadioGroupExample() {
    val options = listOf("Male", "Female", "Others")
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Your selected option: $selectedOption",
                color = Color.Blue
            )
        }
    }
}

@Composable
fun ClickableLabelCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
            Text(text = "Accept Terms", modifier = Modifier.padding(start = 3.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (checked) {
                Text(text = "Terms accepted successfully by user")
            }
        }
    }

}

@Composable
fun MultipleCheckBoxExample() {
    var java by remember { mutableStateOf(false) }
    var kotlin by remember { mutableStateOf(false) }
    var python by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = java,
                onCheckedChange = { java = it }
            )
            Text(text = "Java", modifier = Modifier.padding(start = 8.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = kotlin,
                onCheckedChange = { kotlin = it }
            )
            Text(text = "Kotlin", modifier = Modifier.padding(start = 8.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = python,
                onCheckedChange = { python = it }
            )
            Text(text = "Python", modifier = Modifier.padding(start = 8.dp))
        }

//        if(java) Text(text = "Your selected option is Java")
//        else if(kotlin) Text(text = "Your selected option is Kotlin")
//        else if (python)Text(text = "Your selected option is Python")
//        else Text(text = "Your selected option is ")

    }
}

@Composable
fun MultipleCheckBox() {
    val options = listOf("Java", "Kotlin", "Python")
    val checkStates = remember { mutableStateMapOf<String, Boolean>() }

    options.forEach { option ->
        if (!checkStates.containsKey(option)) checkStates[option] = false
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkStates[option] == true,
                    onCheckedChange = { checkStates[option] = it }
                )
                Text(text = option, Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun ImplicitIntentScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:9845624694")
                }
                context.startActivity(intent)
            }
        ) {
            Text("Dial Number")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:4644687867684")
                    putExtra("sms_body", "This is the message")
                }
                context.startActivity(intent)
            }
        ) {
            Text("Send Message (SMS)")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                context.startActivity(intent)
            }
        ) {
            Text("Pick Image")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:test@example.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Test Email")
                    putExtra(Intent.EXTRA_TEXT, "This is a test mail")
                }
                context.startActivity(intent)
            }
        ) {
            Text("Send Email")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Sharing Text")
                }
                context.startActivity(intent)
            }
        ) {
            Text("Send Text")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(intent)
            }
        ) { Text("Open Camera") }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "document/pdf"
                }
                context.startActivity(intent)
            }
        ) { Text("Open Document") }
    }
}

@Composable
fun SendDataScreen() {
    val context = LocalContext.current;
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") }
        )
        Spacer(Modifier.height(15.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email") }
        )
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = {
                val intent = Intent(
                    context,
                    ExplicitExTwo::class.java
                ).apply {
                    putExtra("name_key", name)
                    putExtra("email_key", email)
                }
                context.startActivity(intent)
            }
        ) {
            Text("Send to Next Activity")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSwitch() {
    SendDataScreen()
}
