package com.spydevs.fiestonvirtual.ui.ranking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.ranking.adapter.RankingItemAdapter
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.activity_ranking.toolbar
import kotlinx.android.synthetic.main.content_ranking.*
import org.koin.android.ext.android.inject

class RankingActivity : AppCompatActivity(R.layout.activity_ranking) {

    private val rankingItemAdapter: RankingItemAdapter by lazy {
        RankingItemAdapter()
    }
    private val dialogProgress by lazy {
        setupLoadingAlertDialog()
    }

    private val viewModel: RankingViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolBar()
        setUpGetRanking()
        setUpRankingListView()
        viewModel.getRanking()
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setUpRankingListView() {
        ranking_rv.adapter = rankingItemAdapter
    }

    private fun setUpGetRanking() {
        viewModel.getRanking.observe(this, Observer {
            when (it) {
                is RankingResult.GetRanking.Loading -> {
                    if (it.show) {
                        dialogProgress.show()
                    } else {
                        dialogProgress.dismiss()
                    }
                }
                is RankingResult.GetRanking.Success -> {
                    rankingItemAdapter.addData(it.ranking)
                    ranking_totalScore_tv.text =
                        getString(R.string.ranking_total_score, it.totalScore)
                }
                is RankingResult.GetRanking.Error -> {
                    setupAlertDialog(
                        title = it.error.title,
                        message = it.error.message
                    )
                }
            }
        })
    }

}