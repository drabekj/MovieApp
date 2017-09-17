package com.strvacademy.drabekj.moviestrv.utils.basecomponents

import android.databinding.ViewDataBinding
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import org.alfonz.mvvm.AlfonzBindingFragment


abstract class BaseFragment<V: BaseView, VM: BaseViewModel<V>, DB: ViewDataBinding>:
        AlfonzBindingFragment<V, VM, DB>(), BaseView {

    override fun showToast(message: String) {
        Toast.makeText(MoviesApplication.getContext(), message, Toast.LENGTH_SHORT).show()
    }
}