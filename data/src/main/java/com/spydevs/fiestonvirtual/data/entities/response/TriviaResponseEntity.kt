package com.spydevs.fiestonvirtual.data.entities.response

data class TriviaResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val question: List<Question>
    ) {
        data class Question(
            val idQuestion: Int,
            val question: String,
            val questionAlternative: List<QuestionAlternative>,
            val questionImage: String,
            val questionName: String,
            val questionScore: Int,
            val questionStatus: Int,
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