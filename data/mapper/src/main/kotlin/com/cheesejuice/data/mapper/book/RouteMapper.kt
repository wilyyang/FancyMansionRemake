package com.cheesejuice.data.mapper.book

import com.cheesejuice.core.common.NOT_ASSIGN_PAGE
import com.cheesejuice.domain.entity.readbook.book.RouteEntity

data class RouteMapper(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionMapper> = listOf()
)

fun RouteMapper.asEntity() = RouteEntity(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asEntity() }.toMutableList()
)

fun RouteEntity.asMapper() = RouteMapper(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asMapper() }
)