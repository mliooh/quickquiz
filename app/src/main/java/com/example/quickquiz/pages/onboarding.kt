package com.example.quickquiz.pages

import QuizViewModel
import android.R
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    ){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth() .padding(all = 10.dp),
        label = { Text(label) },
        placeholder = {Text(placeholder)}

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,

){
    var expanded by remember {
        mutableStateOf(false)

    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()) , verticalArrangement = Arrangement.Center
    ){
        //Text(text = label)
        Box(
            modifier = Modifier

                //.clickable { expanded = true }
                .padding(6.dp)
                //.border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
        ) {
            //Text(text = selectedOption.ifEmpty { "Select $label" })


        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )




        DropdownMenu(
            expanded = expanded,
            modifier = Modifier .padding(6.dp),
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )

            } } }}}}
@Composable
fun TestScreen(viewModel: QuizViewModel = viewModel()) {
    val quizCases = viewModel.quizCases.value
    val isLoading = viewModel.isLoading.value



    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){ Text("Welcome To QuicQuiz")
// difficulty

        CustomMenu(
            label = "Difficulty",
            options = listOf("Easy", "Medium", "Hard"),
            selectedOption =  viewModel.selectedDifficulty,
            onOptionSelected = { viewModel.selectedDifficulty = it }
        )

// age

        CustomMenu(
            label = "Age",
            options = listOf("1", "2", "3"),
            selectedOption = viewModel.selectedAge,
            onOptionSelected = {viewModel.selectedAge = it}
        )


// language

        CustomMenu(
            label = "Language",
            options = listOf("English", "Swahili", "spanish"),
            selectedOption = viewModel.selectedLanguage,
            onOptionSelected = {viewModel.selectedLanguage = it}
        )


// gender

        CustomMenu(
            label = "Gender",
            options = listOf("Male", "Female"),
            selectedOption = viewModel.selectedGender,
            onOptionSelected = {viewModel.selectedGender = it}
        )

// question type

        CustomMenu(
            label = "Gender",
            options = listOf("Study", "Behavioral", "Hiring"),
            selectedOption = viewModel.selectedQuestionType,
            onOptionSelected = {viewModel.selectedQuestionType = it}
        )

// question type

        CustomMenu(
            label = "Sub Type",
            options = listOf("Subject Mastery", "Critical Thinking", "Practical Application"),
            selectedOption = viewModel.selectedSubjectMastery,
            onOptionSelected = {viewModel.selectedSubjectMastery = it}
        )

// subject

        CustomTextField(
            value = viewModel.subject,
            onValueChange = {viewModel.subject = it},
            label = "Subject",
            placeholder = "Enter Subject... "
        )


// role

        CustomTextField(
            value = viewModel.role,
            onValueChange = {viewModel.role = it},
            label = "Role (Will be used for analysis part):",
            placeholder = "Enter Role (e.g Student)"
        )

// fetch questions button

        Button(modifier = Modifier.fillMaxWidth(), onClick = {viewModel.fetchQuestions() }) {
            Text("Get Quiz")
        }

        // Show loading spinner
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }

        }

        if (quizCases.isNotEmpty()) {

            Text("Received ${quizCases.size} cases:")
            quizCases.forEachIndexed { index, case ->
                Text("${index + 1}. ${case.case}")
            }
        } else if(!isLoading) {
            Text("No quiz data yet.")
        }


    }

}


// quiz prompt generation
/*
    @Composable
    fun DropdownWithSeparateTextField() {
        // Dropdown state
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Option 1", "Option 2", "Option 3",)
        var selectedOption by remember { mutableStateOf(options[0]) }

        // Text field state
        var textValue by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Dropdown Menu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select an option") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false

                            }
                        )
                    }
                }
            }



            // 3. Display final value
            val displayValue = selectedOption
            Text(
                text = "Selected: $displayValue",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }


    }

*/
