package com.mariana.catapichallenge.catlist.util

sealed class Screen(val rout: String) {
    object Home: Screen("main")
    object CatList: Screen("catList")
    object FavoriteCat: Screen("favoriteCat")
    object Details: Screen("details")
}