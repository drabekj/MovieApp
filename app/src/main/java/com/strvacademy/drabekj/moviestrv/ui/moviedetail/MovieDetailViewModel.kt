package com.strvacademy.drabekj.moviestrv.ui.moviedetail

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
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.listener.OnItemClickListener
import com.strvacademy.drabekj.moviestrv.model.MoviesDataResponse
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiService
import me.tatarka.bindingcollectionadapter2.BR
import org.alfonz.utility.Logcat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailViewModel: BaseViewModel<MovieDetailView>() {
	var id: Int? = null
	val state = ObservableField<Int>()
	val movie = ObservableField<Movie>()

	val gallery: ObservableList<String> = ObservableArrayList()
	val itemBindingGallery = ItemBinding.of<String>(BR.item, R.layout.fragment_movie_detail_gallery_list_item)!!

	val cast: ObservableList<MovieItemViewModel> = ObservableArrayList()
	val onCastClickListener = OnItemClickListener<MovieItemViewModel> {
		item -> Toast.makeText(MoviesApplication.getContext(), "click " + item.actor.get().name, Toast.LENGTH_SHORT).show()
	}
	val itemBindingCast = ItemBinding.of<MovieItemViewModel>(BR.itemViewModel, R.layout.fragment_movie_detail_cast_list_item)
			.bindExtra(BR.listener, onCastClickListener)!!

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
		retrofitRequest()
    }

	private fun retrofitRequest() {
		TheMovieDbApiService.newInstance()!!.getMovieDetailById(id!!).enqueue(object : Callback<Movie> {
			override fun onFailure(call: Call<Movie>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
				onErrorLoadingData()
			}

			override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
				Logcat.d("retrofit success: " + response!!.toString())
				onLoadData(response.body())
			}
		})
	}

    private fun  onLoadData(m: Movie?) {
        movie.set(m!!)

//		updateGallery(m)
//		updateCast(m)

        // show content
        if(movie.get() != null)
            state.set(StatefulLayout.CONTENT)
        else
            state.set(StatefulLayout.EMPTY)
    }

	fun onErrorLoadingData() {
		state.set(StatefulLayout.EMPTY)
	}

	private fun updateGallery(m: Movie) {
		gallery.clear()
		gallery.addAll(m.gallery)
	}

	private fun updateCast(m: Movie) {
		cast.clear()
		m.cast.mapTo(cast) { MovieItemViewModel(it) }
	}
}