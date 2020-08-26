package com.spydevs.fiestonvirtual.ui.main.ranking.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.ranking.Ranking
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.layout_item_ranking.view.*

class RankingItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(ranking: Ranking) {
        itemView.apply {
            userLogo_iv.loadUrl(ranking.userLogo)
            name_tv.text = ranking.userName
            score_tv.text = ranking.totalScore.toString()
            position_tv.text = ranking.position.toString()

            if (ranking.position == 1) {
                position_tv.highlightColor = resources.getColor(R.color.yellow_600, null)
            }

        }


    }
}