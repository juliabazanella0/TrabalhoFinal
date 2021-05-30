package com.example.trabalho_final.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho_final.R
import com.example.trabalho_final.RankingFragment
import com.example.trabalho_final.dao.RankingDAO
import com.example.trabalho_final.models.Ranking
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter(context: RankingFragment): RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private var ranking: List<Ranking> = listOf()
    private var dao: RankingDAO = RankingDAO()

    override fun getItemCount() = ranking.size

//    init {
//        dao.getAll { response ->
//            this.ranking = response.data.ranking
//            notifyDataSetChanged()
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rankingPosition = ranking[position]
        holder.fillView(rankingPosition)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(rankingPosition: Ranking) {
            itemView.lbPosition.text = rankingPosition.user
            itemView.lbScore.text = rankingPosition.score.toString()
        }
    }
}