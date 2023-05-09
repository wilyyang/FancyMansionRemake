package com.cheesejuice.fancymansion.core.data.source.storage.model

import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_PAGE
import com.cheesejuice.fancymansion.core.entity.book.RouteEntity

data class RouteData(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionData> = listOf()
)

fun RouteData.asEntity() = RouteEntity(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asEntity() }.toMutableList()
)

fun RouteEntity.asData() = RouteData(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asData() }
)