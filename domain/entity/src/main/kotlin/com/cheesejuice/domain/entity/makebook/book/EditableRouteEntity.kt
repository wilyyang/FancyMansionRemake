package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.core.common.NOT_ASSIGN_PAGE
import com.cheesejuice.domain.entity.readbook.book.RouteEntity

data class EditableRouteEntity(
    var routeId : Long,
    var routePageId : Long = NOT_ASSIGN_PAGE,
    var routeConditions : MutableList<EditableConditionEntity> = mutableListOf()
)

fun RouteEntity.toEditable() = EditableRouteEntity(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.toEditable() }.toMutableList()
)