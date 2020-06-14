package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.model.trivia.TriviaModel
import com.spydevs.fiestonvirtual.ui.main.trivia.option.OptionAdapter
import kotlinx.android.synthetic.main.fragment_page_trivia.*

class TriviaPageFragment(
    private var triviaModel: TriviaModel
) : Fragment(R.layout.fragment_page_trivia) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        triviaPageFragment_tv_title.text = triviaModel.title
        triviaPageFragment_tv_points.text = triviaModel.point
        triviaPageFragment_rv_options.adapter = OptionAdapter().apply {
            triviaModel.options?.let {
                addData(it)
            }
        }
    }

}