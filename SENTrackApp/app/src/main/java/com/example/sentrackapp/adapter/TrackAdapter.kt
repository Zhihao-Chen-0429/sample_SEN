package com.example.sentrackapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sentrackapp.databinding.TrackListItemBinding
import com.example.sentrackapp.model.TrackItem
import com.example.sentrackapp.services.POSTER_BASE_URL

class TrackAdapter(private val trackList: List<TrackItem>, private val mContext: Context) :
    RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class ViewHolder(view: TrackListItemBinding): RecyclerView.ViewHolder(view.root) {
        val title = view.cvMovieTitle
        val date = view.cvMovieReleaseDate
        val image = view.cvIvMoviePoster
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = TrackListItemBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = trackList[position].title
        holder.date.text = trackList[position].release_date
        Glide.with(mContext)
            .load(POSTER_BASE_URL + trackList[position].poster_path)
            .into(holder.image)
    }

    override fun getItemCount(): Int = trackList.size

}