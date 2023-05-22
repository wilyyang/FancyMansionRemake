package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.fancymansion.domain.entity.readbook.book.ChoiceItemEntity

data class ChoiceItemMapper(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionMapper> = listOf(),
    val routes : List<RouteMapper> = listOf()
)

fun ChoiceItemMapper.asEntity() = ChoiceItemEntity(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asEntity() }.toMutableList(),
    routes = routes.map { it.asEntity() }.toMutableList()
)

fun ChoiceItemEntity.asMapper() = ChoiceItemMapper(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asMapper() },
    routes = routes.map { it.asMapper() }
)