package com.strvacademy.drabekj.movieapp.utils.basecomponents

import android.widget.Toast
import com.strvacademy.drabekj.movieapp.MoviesApplication
import org.alfonz.mvvm.AlfonzActivity


abstract class BaseActivity: AlfonzActivity() {
    fun showToast(message: String) {
        Toast.makeText(MoviesApplication.context, message, Toast.LENGTH_SHORT).show()
    }
}