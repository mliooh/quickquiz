package com.example.quickquiz.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable

fun TextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label)},
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )
}