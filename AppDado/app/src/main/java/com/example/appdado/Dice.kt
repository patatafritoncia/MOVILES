package com.example.appdado

import android.widget.TextView

class Dice (val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }

}