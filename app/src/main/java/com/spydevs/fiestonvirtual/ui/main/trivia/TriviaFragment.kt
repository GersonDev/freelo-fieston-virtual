package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.util.ZoomOutPageTransformer
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.fragment_trivia.*
import org.koin.android.ext.android.inject

class TriviaFragment : Fragment(R.layout.fragment_trivia) {

    private val triviaViewModel: TriviaViewModel by inject()
    private lateinit var triviaModelList: List<Trivia>

    private val triviaPagerAdapter: TriviaPagerAdapter by lazy {
        TriviaPagerAdapter(this) { positionAnswer ->
            isCorrectAnswer(positionAnswer)
        }
    }

    private val dialogProgress by lazy {
        activity?.setupLoadingAlertDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        subscribeToTrivia()
        subscribeToAnyError()
        subscribeToLoading()
        subscribeToAnswerTriviaSuccessful()
        subscribeToAnswerTriviaError()
        triviaViewModel.getTrivia()
    }

    private fun setUpViews() {
        triviaFragment_vp.adapter = triviaPagerAdapter
        triviaFragment_vp.setPageTransformer(ZoomOutPageTransformer())
        triviaFragment_vp.isUserInputEnabled = false
    }

    private fun subscribeToTrivia() {
        triviaViewModel.trivia.observe(viewLifecycleOwner, Observer {
            triviaPagerAdapter.addAllData(it)
            triviaModelList = it
        })
    }

    private fun subscribeToAnyError() {
        triviaViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun subscribeToLoading() {
        this.triviaViewModel.loading.observe(
            viewLifecycleOwner,
            Observer {
                if ((it as TriviaResult.Loading).show) {
                    this.dialogProgress?.show()
                } else {
                    this.dialogProgress?.dismiss()
                }
            }
        )
    }

    private fun subscribeToAnswerTriviaSuccessful() {
        this.triviaViewModel.answerTriviaSuccessful.observe(
            viewLifecycleOwner,
            Observer {
                activity?.setupAlertDialog(
                    message = (it as TriviaResult.AnswerTriviaSuccessful).message,
                    onPositiveButtonClick = { nextPage() }
                )
            }
        )
    }

    private fun subscribeToAnswerTriviaError() {
        this.triviaViewModel.answerTriviaError.observe(
            viewLifecycleOwner,
            Observer {
                activity?.setupAlertDialog(
                    message = (it as TriviaResult.AnswerTriviaError).message
                )
            }
        )
    }

    private fun isCorrectAnswer(positionAnswer: Int) {
        triviaViewModel.answerTrivia(
            triviaModelList[triviaFragment_vp.currentItem]
                .questionAlternative[positionAnswer]
                .alternativeId
        )
    }

    private fun nextPage() {
        triviaFragment_vp.setCurrentItem(triviaFragment_vp.currentItem + 1, true);
    }
}