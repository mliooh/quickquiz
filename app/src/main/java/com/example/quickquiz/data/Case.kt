package com.example.quickquiz.data

data class QuizResponse(
    val data: List<QuizCase>
)

data class QuizCase(
    val answer: String?,
    val case: String,
    val case_id: String,
    val generated_image_data: String?,
    val optimal: Int,
    val options: List<QuizOption>
)

data class QuizOption(
    val number: Int,
    val option: String,
    val option_id: String,
    val happiness: Int,
    val health: Int,
    val karma: Int,
    val knowledge: Int,
    val personal_growth: Int,
    val relationships: Int,
    val social_responsibility: Int,
    val time_management: Int,
    val environmental_impact: Int,
    val wealth: Int
)
