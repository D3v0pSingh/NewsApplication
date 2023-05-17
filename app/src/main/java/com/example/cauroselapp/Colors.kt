package com.example.cauroselapp

object Colors {

    val color = arrayOf("#DFFF00"," #FFBF00", "#FF7F50" , "#DE3163", "#9FE2BF", " #40E0D0", " #6495ED")

    var colorIndex = 1
    fun getColor(): String {
        return color[colorIndex++ % color.size]
    }
}