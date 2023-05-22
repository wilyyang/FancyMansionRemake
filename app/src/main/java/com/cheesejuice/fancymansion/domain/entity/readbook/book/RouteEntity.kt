package com.cheesejuice.fancymansion.domain.entity.readbook.book

import com.cheesejuice.core.common.NOT_ASSIGN_PAGE

data class RouteEntity(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionEntity> = listOf()
)