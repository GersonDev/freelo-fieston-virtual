package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.TriviaModel
import com.spydevs.fiestonvirtual.model.trivia.AnswerModel
import kotlinx.android.synthetic.main.fragment_trivia.*

class TriviaFragment : Fragment(R.layout.fragment_trivia) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fa = TriviaPagerAdapter(this)
        triviaFragment_vp.adapter = fa
        val triviaModelList = listOf(
            TriviaModel(
                "¿Cuantos tiempo estudias?",
                "son 10 puntos",
                mutableListOf(
                    AnswerModel("1 hora"),
                    AnswerModel("2 horas"),
                    AnswerModel("3 horas")
                )
            ),
            TriviaModel(
                "¿Cuantos años tienes?",
                "son 20 puntos",
                mutableListOf(
                    AnswerModel("10 años"),
                    AnswerModel("15 años"),
                    AnswerModel("30 años")
                )
            ),
            TriviaModel(
                "¿Sales a correr?",
                "son 30 puntos"
                ,
                mutableListOf(
                    AnswerModel("si"),
                    AnswerModel("No"),
                    AnswerModel("a veces")
                )
            ),
            TriviaModel(
                "¿Te gusta el chocolate?",
                "son 40 puntos",
                mutableListOf(
                    AnswerModel("si"),
                    AnswerModel("no")
                )
            )
        )
        fa.addData(triviaModelList)
    }

}