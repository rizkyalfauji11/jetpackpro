package com.code.alpha.jetpro.fragment.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.local.entity.Movie
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class DetailMovieFragment : Fragment() {

    companion object {
        fun newInstance(movie: Movie): DetailMovieFragment {
            val fragment = DetailMovieFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constant.movie, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var movie: Movie
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(Constant.movie) as Movie
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Glide.with(this).load(movie.photo).into(img_cover)
        Glide.with(this).load(movie.photo).into(photo)
        title_movie.text = movie.title
        year_movie.text = movie.year
        body.text = movie.desc
        val lengthOfMinutes = String.format(resources.getString(R.string.length_time), movie.lengthOfTime.toString())
        length.text = lengthOfMinutes
        rating.rating = (movie.rating).toString().toFloat() / 2
    }

}
