package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.TriviaResponseEntity
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object TriviaMapper : Mapper<TriviaResponseEntity, List<Trivia>>() {
    override fun convertFromInitial(i: TriviaResponseEntity): List<Trivia> {
        return i.data.map {
            Trivia(
                it.question.idQuestion,
                it.question.questionType,
                it.question.questionName,
                it.question.question,
                it.question.questionScore,
                it.question.questionImage,
                it.question.questionStatus,
                it.question.questionAlternative.map { questionAlternative ->
                    Trivia.Alternative(
                        questionAlternative.idAlternative,
                        questionAlternative.alternativeDescription,
                        questionAlternative.alternativeAnswer
                    )
                }
            )
        }
    }
}