package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.core.common.NOT_ASSIGN_PAGE

data class RouteMapper(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionMapper> = listOf()
)