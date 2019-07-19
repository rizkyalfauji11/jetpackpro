package com.code.alpha.jetpro.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.adapter.JetProViewPagerAdapter
import com.code.alpha.jetpro.fragment.home.MovieFragment
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.MoviesData
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.subtitle = resources.getString(R.string.recommended_movies)
        val jetProPagerAdapter = JetProViewPagerAdapter(supportFragmentManager)
        jetProPagerAdapter.addTab(MovieFragment.newInstance(Constant.movie), resources.getString(R.string.string_movie))
        jetProPagerAdapter.addTab(
            MovieFragment.newInstance(Constant.tv_show),
            resources.getString(R.string.string_tv_show)
        )
        viewPager.adapter = jetProPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
