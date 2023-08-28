package com.example.cs411final.dataModel

data class movieList(
    var results : List<movie>,
    var page : Int,
    var total_pages : Int,
    var total_results : Int
) {
}