package com.evalenzuela.navigation.navigation

object Routes {

    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val ADD_RECIPE = "add_recipe"
    const val PROFILE = "profile"
    const val FAVORITE  = "favoritos"
    const val DETAIL = "detail/{itemId}"
    fun detailRoute(itemId: Int) = "detail/$itemId"

}