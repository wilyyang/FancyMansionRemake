package com.cheesejuice.fancymansion.ui.nav

interface NavDestination {
    val route: String
}

object TestScreen : NavDestination {
    override val route = "test"
}

object ReadPageScreen : NavDestination {
    override val route = "read_page"
}