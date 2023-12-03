package com.sviatkuzbyt.library.ui.elements

import android.content.Context
import android.widget.Toast

fun makeToast(textId: Int, context: Context){
    Toast.makeText(context, context.getString(textId), Toast.LENGTH_LONG).show()
}

fun makeToast(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}