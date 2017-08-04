package com.strvacademy.drabekj.moviestrv.mainActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.utils.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar(INDICATOR_NONE, "Movies")
    }

    // TODO toolbar (Is this how it's supposed to be done with Alfonz?)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> {
                showToast("Search Action")
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
