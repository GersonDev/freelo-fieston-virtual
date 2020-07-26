package com.spydevs.fiestonvirtual.ui.main.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.trivia.Trivia
import com.spydevs.fiestonvirtual.util.ZoomOutPageTransformer
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.fragment_trivia.*
import kotlinx.android.synthetic.main.layout_onboarding_trivia.*
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
        setUpViewPager()
        subscribeToGetTriviaSuccess()
        subscribeToGetTriviaError()
        subscribeToLoading()
        subscribeToAnswerTriviaSuccess()
        subscribeToAnswerTriviaError()
        setUpPlayButton()
    }

    private fun setUpPlayButton() {
        onboarding_trivia_btn.setOnClickListener {
            triviaViewModel.getTrivia()
        }
    }

    private fun setUpViewPager() {
        triviaFragment_vp.adapter = triviaPagerAdapter
        triviaFragment_vp.setPageTransformer(ZoomOutPageTransformer())
        triviaFragment_vp.isUserInputEnabled = false
    }

    private fun subscribeToGetTriviaSuccess() {
        triviaViewModel.getTriviaSuccess.observe(viewLifecycleOwner, Observer {
            onboarding_trivia_cl.visibility = View.INVISIBLE
            triviaFragment_vp.visibility = View.VISIBLE
            triviaPagerAdapter.addAllData((it as TriviaResult.GetTrivia.Success).triviaList)
            triviaModelList = it.triviaList
        })
    }

    private fun subscribeToGetTriviaError() {
        triviaViewModel.getTriviaError.observe(viewLifecycleOwner, Observer {
            activity?.setupAlertDialog(
                message = (it as TriviaResult.GetTrivia.Error).text
            )
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

    private fun subscribeToAnswerTriviaSuccess() {
        this.triviaViewModel.answerTriviaSuccess.observe(
            viewLifecycleOwner,
            Observer {
                val message = getString(
                    R.string.trivia_message_of_the_answer,
                    (it as TriviaResult.AnswerTrivia.Success).message,
                    it.userTotalScore.toString()
                )
                //if last page to pager then show onboarding trivia else next page.
                if (triviaFragment_vp.currentItem + 1 == triviaPagerAdapter.itemCount) {
                    activity?.setupAlertDialog(
                        message = message,
                        onPositiveButtonClick = { showOnboardingTrivia() }
                    )
                } else {
                    activity?.setupAlertDialog(
                        message = message,
                        onPositiveButtonClick = { nextPageToPager() }
                    )
                }

            }
        )
    }

    private fun subscribeToAnswerTriviaError() {
        this.triviaViewModel.answerTriviaError.observe(
            viewLifecycleOwner,
            Observer {
                activity?.setupAlertDialog(
                    message = (it as TriviaResult.AnswerTrivia.Error).message
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

    private fun nextPageToPager() {
        triviaFragment_vp.setCurrentItem(triviaFragment_vp.currentItem + 1, true)
    }

    private fun showOnboardingTrivia() {
        triviaFragment_vp.visibility = View.INVISIBLE
        onboarding_trivia_cl.visibility = View.VISIBLE
    }
}