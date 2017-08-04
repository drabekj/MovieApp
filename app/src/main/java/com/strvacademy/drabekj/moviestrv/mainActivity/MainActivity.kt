package com.strvacademy.drabekj.moviestrv.mainActivity

import android.os.Bundle
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(INDICATOR_NONE, "Movies")
    }
}
