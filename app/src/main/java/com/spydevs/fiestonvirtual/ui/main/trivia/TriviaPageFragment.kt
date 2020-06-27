package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.ui.main.trivia.answer.AnswerAdapter
import kotlinx.android.synthetic.main.fragment_page_trivia.*

class TriviaPageFragment(
    private var trivia: Trivia
) : Fragment(R.layout.fragment_page_trivia) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        triviaPageFragment_tv_title.text = trivia.questionName
        triviaPageFragment_tv_points.text = trivia.question
        triviaPageFragment_rv_options.adapter = AnswerAdapter().apply {
            addData(trivia.questionAlternative)
        }
    }

}