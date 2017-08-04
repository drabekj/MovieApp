package com.strvacademy.drabekj.moviestrv.detailActivity

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout


class MovieDetailViewModel: BaseViewModel<MovieDetailView>() {
    val state = ObservableField<Int>()
    val movie = ObservableField<Movie>()
    var id: Int? = null

    val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


    override fun onStart() {
        super.onStart()
        if (movie.get() == null)
            loadData(id!!)
    }

    private fun loadData(id: Int) {
        // show progress
        state.set(StatefulLayout.PROGRESS)

        // load data from data provider...
        onLoadData(dataSource.getMovieById(id))
    }

    private fun  onLoadData(m: Movie?) {
        movie.set(m)

        // show content
        if(movie.get() != null)
            state.set(StatefulLayout.CONTENT)
        else
            state.set(StatefulLayout.EMPTY)
    }


}