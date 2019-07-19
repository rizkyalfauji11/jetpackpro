package com.code.alpha.jetpro.fragment.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.activity.DetailMovieActivity
import com.code.alpha.jetpro.adapter.MovieListAdapter
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.utils.SpacesItemDecoration
import com.code.alpha.jetpro.viewmodel.ViewModelFactory
import com.code.alpha.jetpro.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment(), MovieCallback {

    companion object {
        fun newInstance(type: String): MovieFragment {
            val fragment = MovieFragment()
            val bundle = Bundle()
            bundle.putString(Constant.type, type)
            fragment.arguments = bundle
            return fragment
        }

        @NonNull
        private fun obtainViewModel(activity: FragmentActivity): MovieViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
        }
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieListAdapter
    private var movieList: MutableList<Movie> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovie.layoutManager = LinearLayoutManager(requireContext())
        rvMovie.addItemDecoration(SpacesItemDecoration(16))
        adapter = MovieListAdapter(requireContext(), movieList) {
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(Constant.movie, it)
            startActivity(intent)
        }
        rvMovie.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { viewModel = obtainViewModel(it) }
        arguments?.getString(Constant.type)?.let {
            viewModel.movies.observe(this, Observer {
                Log.e("JDI", "DISINI")
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            movieList.clear()
                            it.data?.let { it1 -> movieList.addAll(it1) }
                            adapter.notifyDataSetChanged()
                        }

                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), i.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }

    }

    override fun showMovies(list: List<Movie>?) {
        movieList.clear()
        list?.let { movieList.addAll(it) }
        adapter.notifyDataSetChanged()
    }


}
