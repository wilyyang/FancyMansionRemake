package com.cheesejuice.fancymansion.core.data.source.storage.model

import com.cheesejuice.fancymansion.core.entity.book.ChoiceItemEntity

data class ChoiceItemData(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionData> = listOf(),
    val routes : List<RouteData> = listOf()
)

fun ChoiceItemData.asEntity() = ChoiceItemEntity(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asEntity() }.toMutableList(),
    routes = routes.map { it.asEntity() }.toMutableList()
)

fun ChoiceItemEntity.asData() = ChoiceItemData(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asData() },
    routes = routes.map { it.asData() }
)