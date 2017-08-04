package com.strvacademy.drabekj.moviestrv.mainActivity

import android.databinding.ObservableArrayList
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import android.databinding.ObservableField
import org.alfonz.view.StatefulLayout
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MovieDataSource
import com.strvacademy.drabekj.moviestrv.model.MovieRepository
import com.strvacademy.drabekj.moviestrv.model.local.MovieDummyData


abstract class MainViewModel : BaseViewModel<MainView>() {
    val state = ObservableField<Int>()
    var movies = ObservableArrayList<Movie>()

    val dataSource: MovieDataSource = MovieRepository(MovieDummyData())


    override fun onStart() {
        super.onStart()
        if (movies.isEmpty())
            loadData()
    }

    abstract fun loadData()

    fun onLoadData(m: Array<Movie>) {
        // save data
        // TODO do better (observable array replacement)
        movies.clear()
        movies.addAll(m)

        // show content
        if (movies.isEmpty())
            state.set(StatefulLayout.EMPTY)
        else
            state.set(StatefulLayout.CONTENT)
    }
//
//    fun onResponse(call: Call<MessageEntity>, response: Response<MessageEntity>) {
//        runViewAction(object : AlfonzViewModel.ViewAction<MainView> {
//            override fun run(view: MainView) {
//                view.startGreetingActivity(response.body().getText())
//            }
//        })
//    }
}