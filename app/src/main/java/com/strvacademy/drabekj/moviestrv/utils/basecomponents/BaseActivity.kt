package com.strvacademy.drabekj.moviestrv.utils.basecomponents

import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import org.alfonz.mvvm.AlfonzActivity


abstract class BaseActivity: AlfonzActivity() {
    fun showToast(message: String) {
        Toast.makeText(MoviesApplication.context, message, Toast.LENGTH_SHORT).show()
    }
}