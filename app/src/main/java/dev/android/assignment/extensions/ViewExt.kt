package dev.android.assignment.extensions

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.BaseInputConnection

fun View.emitKeyPress(keyPressed: String) {
    val key = toKeyCode(keyPressed)

    BaseInputConnection(this, true).apply {
        sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, key))
        sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, key))
    }
}

private fun toKeyCode(value: String): Int = when (value) {
    "0" -> KeyEvent.KEYCODE_0
    "1" -> KeyEvent.KEYCODE_1
    "2" -> KeyEvent.KEYCODE_2
    "3" -> KeyEvent.KEYCODE_3
    "4" -> KeyEvent.KEYCODE_4
    "5" -> KeyEvent.KEYCODE_5
    "6" -> KeyEvent.KEYCODE_6
    "7" -> KeyEvent.KEYCODE_7
    "8" -> KeyEvent.KEYCODE_8
    "9" -> KeyEvent.KEYCODE_9
    "Del" -> KeyEvent.KEYCODE_DEL
    else -> KeyEvent.KEYCODE_CLEAR
}