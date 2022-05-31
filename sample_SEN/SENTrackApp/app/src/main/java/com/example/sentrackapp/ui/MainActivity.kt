package com.example.sentrackapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentrackapp.adapter.TrackAdapter
import com.example.sentrackapp.databinding.ActivityMainBinding
import com.example.sentrackapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
    }

    override fun onResume() {
        super.onResume()
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mViewModel.getTracksFromAPI()
        mViewModel.trackResponse.observe(this,{
            trackResponse ->
            trackResponse?.let {
                Log.i("trackResponse","$trackResponse")
                mBinding?.rvMovieList?.layoutManager = LinearLayoutManager(this)
                mBinding?.rvMovieList?.adapter = TrackAdapter(it.trackLists,this)
            }
        })

    }

}