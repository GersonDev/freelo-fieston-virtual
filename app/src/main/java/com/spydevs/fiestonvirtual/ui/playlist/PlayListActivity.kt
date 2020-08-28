package com.spydevs.fiestonvirtual.ui.playlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.show
import kotlinx.android.synthetic.main.content_play_list.*
import kotlinx.android.synthetic.main.toolbar_simple.*
import org.koin.android.ext.android.inject

class PlayListActivity : AppCompatActivity() {

    private val viewModel: PlayListViewModel by inject()

    private val getPlaylistLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private val requestSongLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private var songPosition = 0

    private val playListAdapter by lazy {
        PlayListAdapter(onSongClicked = { position, song ->
            songPosition = position
            viewModel.requestSong(song.id)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)
        setupViews()
        setUpViewListeners()
        subscribeToCommentsResult()
        viewModel.getPlaylist()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        playlist_rv_songs.apply {
            adapter = playListAdapter
        }
    }

    private fun setUpViewListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun subscribeToCommentsResult() {
        this.viewModel.playlist.observe(this, Observer {
            when (it) {
                is PlaylistResult.GetPlaylist.Success -> {
                    this.playListAdapter.addAllData(it.songs)
                }
                is PlaylistResult.GetPlaylist.Loading -> {
                    this.getPlaylistLoadingDialog.show(it.loading)
                }
                is PlaylistResult.GetPlaylist.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is PlaylistResult.RequestSong.Success -> {
                    this.playListAdapter.updateData(songPosition, it.song)
                    Toast.makeText(this, "Música solicitada", Toast.LENGTH_SHORT).show()
                }
                is PlaylistResult.RequestSong.Loading -> {
                    this.requestSongLoadingDialog.show(it.loading)
                }
                is PlaylistResult.RequestSong.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}