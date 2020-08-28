package com.spydevs.fiestonvirtual.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import kotlinx.android.synthetic.main.item_play_list.view.*

class PlayListAdapter(
    private val onSongClicked: (position: Int, song: Song) -> Unit,
    private val songs: MutableList<Song> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val categoryNormalView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_play_list, parent, false)
        return SongViewHolder(categoryNormalView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongViewHolder).bind(songs[position])
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    inner class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var song: Song

        init {
            itemView.playList_btn_requested.setOnClickListener {
                onSongClicked(adapterPosition, song)
            }
        }

        fun bind(song: Song) {
            this.song = song
            itemView.playList_tv_title.text = song.title
            itemView.playList_tv_author.text = song.band
            itemView.playList_btn_requested.isEnabled = !song.requested
        }
    }

    fun addAllData(songs: List<Song>) {
        this.songs.addAll(songs)
        notifyDataSetChanged()
    }

    fun updateData(position: Int, song: Song) {
        this.songs[position].requested = song.requested
        notifyDataSetChanged()
    }

}