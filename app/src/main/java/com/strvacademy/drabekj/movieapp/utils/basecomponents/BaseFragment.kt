package com.strvacademy.drabekj.movieapp.utils.basecomponents

import android.databinding.ViewDataBinding
import android.widget.Toast
import com.strvacademy.drabekj.movieapp.MoviesApplication
import org.alfonz.mvvm.AlfonzBindingFragment


abstract class BaseFragment<V: BaseView, VM: BaseViewModel<V>, DB: ViewDataBinding>:
        AlfonzBindingFragment<V, VM, DB>(), BaseView {

    override fun showToast(message: String) {
        Toast.makeText(MoviesApplication.context, message, Toast.LENGTH_SHORT).show()
    }
}