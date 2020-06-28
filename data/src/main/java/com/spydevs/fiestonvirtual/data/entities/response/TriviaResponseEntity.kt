package com.spydevs.fiestonvirtual.data.entities.response

data class TriviaResponseEntity(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val question: Question
    ) {
        data class Question(
            val idQuestion: Int,
            val question: String,
            val questionAlternative: List<QuestionAlternative>,
            val questionImage: String,
            val questionName: String,
            val questionScore: Int,
            val questionStatus: Boolean,
            val questionType: String
        ) {
            data class QuestionAlternative(
                val alternativeAnswer: Boolean,
                val alternativeDescription: String,
                val idAlternative: Int
            )
        }
    }
}