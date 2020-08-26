package com.spydevs.fiestonvirtual.ui.main.ranking

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.ranking.adapter.RankingItemAdapter
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog

class RankingActivity : AppCompatActivity(R.layout.activity_ranking) {

    private val rankingItemAdapter: RankingItemAdapter by lazy {
        RankingItemAdapter()
    }
    private val dialogProgress by lazy {
        setupLoadingAlertDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

}