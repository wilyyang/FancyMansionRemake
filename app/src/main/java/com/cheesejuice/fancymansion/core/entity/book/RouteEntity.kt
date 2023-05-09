package com.cheesejuice.fancymansion.core.entity.book

import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_PAGE

data class RouteEntity(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionEntity> = listOf()
)