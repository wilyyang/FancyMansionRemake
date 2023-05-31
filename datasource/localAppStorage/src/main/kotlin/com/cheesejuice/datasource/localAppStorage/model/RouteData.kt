package com.cheesejuice.datasource.localAppStorage.model

import com.cheesejuice.core.common.NOT_ASSIGN_PAGE
import com.cheesejuice.data.mapper.book.RouteMapper

data class RouteData(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionData> = listOf()
)

fun RouteData.asMapper() = RouteMapper(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asMapper() }.toMutableList()
)

fun RouteMapper.asData() = RouteData(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asData() }
)