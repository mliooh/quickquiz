package com.example.quickquiz.pages

import QuizViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickquiz.components.DropdownMenu
import com.example.quickquiz.components.TextField

@Composable

fun HomeScreen(viewModel: QuizViewModel = viewModel()){
    val quizCases = viewModel.quizCases.value
    val isLoading = viewModel.isLoading.value
    //val uiState = viewModel.uiState.collectAsState().value


    Column (modifier = Modifier.padding(30.dp).fillMaxSize().verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top){
        /*TextField(label = "Subject", value = "String") {
           // viewModel.onSubjectChanged(it)
        }*/

        /*DropdownMenu(
            label = "Difficulty",
            options = listOf("Easy, Medium, Hard"),
            selectedOption = "String", //uiState.difficulty,
            onOptionSelected = {
                //viewModel.onDifficultyChanged(it)
            }
        )*/

        Button(onClick = {viewModel.fetchQuestions()}) {
            Text("Get Quiz")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Show loading spinner
        if (isLoading) {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

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