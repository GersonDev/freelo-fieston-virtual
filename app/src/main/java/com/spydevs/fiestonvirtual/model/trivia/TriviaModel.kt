package com.spydevs.fiestonvirtual.model.trivia

data class TriviaModel(
    var title: String? = null,
    var point: String? = null,
    var answers: MutableList<AnswerModel>? = null
)