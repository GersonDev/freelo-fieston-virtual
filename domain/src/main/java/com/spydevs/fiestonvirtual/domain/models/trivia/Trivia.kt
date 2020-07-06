package com.spydevs.fiestonvirtual.domain.models.trivia

data class Trivia(
    val questionId: Int,
    val questionType: String,
    val questionName: String,
    val question: String,
    val questionScore: Int,
    val questionImageUrl: String,
    val questionStatus: Int,
    val questionAlternative: List<Alternative>
) {
    data class Alternative(
        val alternativeId: Int,
        val alternativeDescription: String,
        var isAlternativeAnswer: Boolean,
        var isSelected: Boolean = false
    )
}