package com.strvacademy.drabekj.moviestrv.mainActivity


class MessageEntity(private var text: String) {


    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }
}