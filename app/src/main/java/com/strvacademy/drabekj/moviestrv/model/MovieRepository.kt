package com.strvacademy.drabekj.moviestrv.model

import com.strvacademy.drabekj.moviestrv.listener.OnLoadDataListener
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

    override fun getPopularMovies(listener: OnLoadDataListener<List<Movie>>) {
		mMoviesRemoteDataSource.getPopularMovies().enqueue(object : Callback<MoviesDataResponse> {
			override fun onFailure(call: Call<MoviesDataResponse>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
				listener.errorLoadingData()
			}

			override fun onResponse(call: Call<MoviesDataResponse>?, response: Response<MoviesDataResponse>?) {
				Logcat.d("retrofit successLoadingData: " + response!!.toString())
				listener.onLoadData(response.body()!!.results)
			}
		})
    }

    override fun getNowPlayingMovies(listener: OnLoadDataListener<List<Movie>>) {
		mMoviesRemoteDataSource.getNowPlayingMovies().enqueue(object : Callback<MoviesDataResponse> {
			override fun onFailure(call: Call<MoviesDataResponse>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
				listener.errorLoadingData()
			}

			override fun onResponse(call: Call<MoviesDataResponse>?, response: Response<MoviesDataResponse>?) {
				Logcat.d("retrofit successLoadingData: " + response!!.toString())
				listener.onLoadData(response.body()!!.results)
			}
		})
    }


	override fun getMovieById(id: Int, listener: OnLoadDataListener<Movie>) {
		mMoviesRemoteDataSource.getMovieDetailById(id).enqueue(object : Callback<Movie> {
			override fun onFailure(call: Call<Movie>?, t: Throwable?) {
				Logcat.d("retrofit fail |" + t.toString())
				listener.errorLoadingData()
			}

			override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
				Logcat.d("retrofit successLoadingData: " + response!!.toString())
				listener.onLoadData(response.body()!!)
			}
		})
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