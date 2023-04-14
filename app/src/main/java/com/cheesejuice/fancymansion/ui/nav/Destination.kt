package com.cheesejuice.fancymansion.ui.nav

interface NavDestination {
    val route: String
}

object TestScreen : NavDestination {
    override val route = "test"
}

object LoginScreen : NavDestination {
    override val route = "login"
}

object HomeScreen : NavDestination {
    override val route = "home"
}

object ReadPageScreen : NavDestination {
    override val route = "read_page"
}