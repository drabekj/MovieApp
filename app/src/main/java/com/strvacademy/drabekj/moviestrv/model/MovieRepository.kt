package com.strvacademy.drabekj.moviestrv.model

import com.strvacademy.drabekj.moviestrv.model.local.MovieLocalDataSource
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiProvider
import com.strvacademy.drabekj.moviestrv.model.remote.TheMovieDbApiService
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel
import org.alfonz.utility.Logcat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository: MovieDataSource {

    private val mMoviesLocalDataSource: MovieLocalDataSource
	private val mMoviesRemoteDataSource: TheMovieDbApiService


    constructor(remoteDataSource: TheMovieDbApiService, localDataSource: MovieLocalDataSource) {
		mMoviesRemoteDataSource = remoteDataSource
        mMoviesLocalDataSource = localDataSource
    }

    override fun getPopularMovies(listener :MoviesPageViewModel.onLoadDataListener) {
		mMoviesRemoteDataSource.getPopularMovies().enqueue(object : Callback<MoviesDataResponse> {
			override fun onFailure(call: Call<MoviesDataResponse>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
//				TODO how to differentiate error vs empty data in ViewModel?
				listener.errorLoadingData()
			}

			override fun onResponse(call: Call<MoviesDataResponse>?, response: Response<MoviesDataResponse>?) {
				Logcat.d("retrofit successLoadingData: " + response!!.toString())
				listener.successLoadingData(response.body()!!.results)
			}
		})
    }

    override fun getNowPlayingMovies(): List<Movie> {
        return mMoviesLocalDataSource.getNowPlayingMovies()
    }

    override fun getMovieById(id: Int): Movie? {
        return mMoviesLocalDataSource.getMovieById(id)
    }


    override fun getPopularActors(): Array<Actor> {
        return mMoviesLocalDataSource.getPopularActors()
    }

	override fun getActorById(id: Int): Actor? {
		return mMoviesLocalDataSource.getActorById(id)
	}


	override fun getProfile(): Profile? {
		return mMoviesLocalDataSource.getProfile()
	}


	override fun getFavoriteMoviesByProfile(id: Int): Array<Movie> {
		return mMoviesLocalDataSource.getFavoriteMoviesByProfile(id)
	}
}