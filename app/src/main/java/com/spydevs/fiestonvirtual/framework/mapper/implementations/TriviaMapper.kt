package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.TriviaResponseEntity
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object TriviaMapper : Mapper<TriviaResponseEntity, List<Trivia>>() {
    override fun convertFromInitial(i: TriviaResponseEntity): List<Trivia> {
        return listOf(
            Trivia(
                i.question.idQuestion,
                i.question.questionType,
                i.question.questionName,
                i.question.question,
                i.question.questionScore,
                i.question.questionImage,
                i.question.questionStatus,
                i.question.questionAlternative.map {
                    Trivia.Alternative(
                        it.idAlternative,
                        it.alternativeDescription,
                        it.alternativeAnswer
                    )
                }
            )
        )
    }
}