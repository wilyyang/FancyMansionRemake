package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_PAGE
import com.cheesejuice.fancymansion.core.entity.book.RouteEntity

data class RouteData(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionData> = listOf()
)

fun RouteData.asMapper() = RouteEntity(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asMapper() }.toMutableList()
)

fun RouteEntity.asData() = RouteData(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asData() }
)