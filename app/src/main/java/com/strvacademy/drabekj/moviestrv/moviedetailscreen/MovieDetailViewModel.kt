package com.strvacademy.drabekj.moviestrv.moviedetailscreen

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import org.alfonz.view.StatefulLayout
import me.tatarka.bindingcollectionadapter2.ItemBinding
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.model.Actor
import me.tatarka.bindingcollectionadapter2.BR


class MovieDetailViewModel: BaseViewModel<MovieDetailView>() {
	var id: Int? = null
	val state = ObservableField<Int>()
	val movie = ObservableField<Movie>()

	val gallery: ObservableList<String> = ObservableArrayList()
	val itemBindingGallery = ItemBinding.of<String>(BR.item, R.layout.fragment_movie_detail_gallery_list_item)!!
	val cast: ObservableList<Actor> = ObservableArrayList()
	val itemBindingCast = ItemBinding.of<Actor>(BR.item, R.layout.fragment_movie_detail_cast_list_item)!!

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
        movie.set(m!!)

		updateGallery(m)
		updateCast(m)

        // show content
        if(movie.get() != null)
            state.set(StatefulLayout.CONTENT)
        else
            state.set(StatefulLayout.EMPTY)
    }

	private fun updateGallery(m: Movie) {
		gallery.clear()
		gallery.addAll(m.gallery)
	}

	private fun updateCast(m: Movie) {
		cast.clear()
		cast.addAll(m.cast)
	}
}