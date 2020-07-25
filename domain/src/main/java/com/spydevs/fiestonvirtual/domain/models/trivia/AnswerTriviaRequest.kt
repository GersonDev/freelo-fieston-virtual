package com.spydevs.fiestonvirtual.domain.models.trivia

data class AnswerTriviaRequest(
    private val idAlternative: Int,
    private val idUserSesion: Int
)