package com.example.quickquiz.data

data class GenerateCasesRequest(
    val language: String,
    val age: Int,
    val subject: String,
    val difficulty: String,
    val question_type: String,
    val sub_type: String,
    val role: String,
    val sex: String,
    val allow_image: Boolean,
    val answers: Map<String, String> // case_id to selected option number
)
