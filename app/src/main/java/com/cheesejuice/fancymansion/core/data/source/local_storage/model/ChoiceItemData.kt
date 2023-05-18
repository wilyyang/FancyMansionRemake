package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.entity.book.ChoiceItemEntity

data class ChoiceItemData(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionData> = listOf(),
    val routes : List<RouteData> = listOf()
)

fun ChoiceItemData.asMapper() = ChoiceItemEntity(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asMapper() }.toMutableList(),
    routes = routes.map { it.asMapper() }.toMutableList()
)

fun ChoiceItemEntity.asData() = ChoiceItemData(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asData() },
    routes = routes.map { it.asData() }
)