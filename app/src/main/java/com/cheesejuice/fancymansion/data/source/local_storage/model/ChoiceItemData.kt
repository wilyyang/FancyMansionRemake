package com.cheesejuice.fancymansion.data.source.local_storage.model

import com.cheesejuice.fancymansion.data.repository.mapper.book.ChoiceItemEntity

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