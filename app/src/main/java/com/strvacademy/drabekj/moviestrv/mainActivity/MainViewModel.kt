package com.strvacademy.drabekj.moviestrv.mainActivity

import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel
import android.databinding.ObservableField
import org.alfonz.view.StatefulLayout
import android.support.annotation.NonNull
import android.telecom.Call
import org.alfonz.mvvm.AlfonzViewModel


class MainViewModel : BaseViewModel<MainView>() {
    val state = ObservableField<Int>()
    val message = ObservableField<MessageEntity>()

    override fun onStart() {
        super.onStart()
        if (message.get() == null)
            loadData()
    }

    fun updateMessage() {
        val m = message.get()

        var text = m.getText()
        if (text == "Yes")
           text = "No"
        else
            text = "Yes"
        m.setText(text)
        message.notifyChange()
    }

    private fun loadData() {
        // show progress
        state.set(StatefulLayout.PROGRESS)

        // load data from data provider...
        onLoadData(MessageEntity("No"))
    }

    private fun onLoadData(m: MessageEntity) {
        // save data
        message.set(m)

        // show content
        if (message.get() != null) {
            state.set(StatefulLayout.CONTENT)
        } else {
            state.set(StatefulLayout.EMPTY)
        }
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