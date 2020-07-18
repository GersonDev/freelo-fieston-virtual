package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.TriviaResponseEntity
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object TriviaMapper : Mapper<TriviaResponseEntity, List<Trivia>>() {
    override fun convertFromInitial(i: TriviaResponseEntity): List<Trivia> {
        return i.data.question.map {
            Trivia(
                it.idQuestion,
                it.questionType,
                it.questionName,
                it.question,
                it.questionScore,
                it.questionImage,
                it.questionStatus,
                it.questionAlternative.map { questionAlternative ->
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