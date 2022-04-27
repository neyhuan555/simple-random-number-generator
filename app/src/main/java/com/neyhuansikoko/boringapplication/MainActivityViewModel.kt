package com.neyhuansikoko.boringapplication

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    var min = "1"
    var max = "100"
    var result = "Let's Roll!"

    fun roll() = Random.nextInt(min.toInt(), max.toInt()+1).toString()
    fun checkEmpty() = min.isEmpty() || max.isEmpty()
    fun checkInvalidRange() = min.toInt() > max.toInt()
}