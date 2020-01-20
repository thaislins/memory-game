package com.example.memorygame.modules.score.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.databinding.ItemScoreBinding
import com.example.memorygame.modules.score.model.Score

class ScoreAdapter(
    private var scores: List<Score?>
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>(),
    AdapterScoresContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemScoreBinding.inflate(inflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        scores[position]?.let { holder.bind(it) }
    }

    override fun setScoreList(list: List<Score>?) {
        if (list != null) {
            this.scores = list
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    class ViewHolder(private val binding: ItemScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(score: Score) {
            binding.score = score
            binding.executePendingBindings()
        }
    }
}