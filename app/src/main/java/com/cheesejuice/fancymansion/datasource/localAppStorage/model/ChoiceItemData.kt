package com.cheesejuice.fancymansion.datasource.localAppStorage.model

import com.cheesejuice.fancymansion.data.mapper.book.ChoiceItemMapper

data class ChoiceItemData(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionData> = listOf(),
    val routes : List<RouteData> = listOf()
)

fun ChoiceItemData.asMapper() = ChoiceItemMapper(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asMapper() }.toMutableList(),
    routes = routes.map { it.asMapper() }.toMutableList()
)

fun ChoiceItemMapper.asData() = ChoiceItemData(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asData() },
    routes = routes.map { it.asData() }
)