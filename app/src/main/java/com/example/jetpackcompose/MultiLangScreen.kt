package com.example.jetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showSystemUi = true)
fun MultilangScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = stringResource(R.string.welcome))

        Button(onClick = {}) {
            Text(text = stringResource(R.string.login))
        }

        Text(text = stringResource(R.string.hello_user,"Android"))

        val count = 1
        Text(
            text = pluralStringResource(
                id = R.plurals.item_count,
                count = count,
                count
            )
        )
    }
}